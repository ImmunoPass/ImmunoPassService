package com.immunopass.service;

import static com.immunopass.utils.ParameterCheckUtil.checkEmptyOrNull;
import static com.immunopass.utils.ParameterCheckUtil.checkLength;
import static com.immunopass.utils.ParameterCheckUtil.cleanMobileNumber;
import static com.immunopass.utils.ParameterCheckUtil.cleanName;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.immunopass.controller.OrderController;
import com.immunopass.entity.OrderEntity;
import com.immunopass.enums.IDType;
import com.immunopass.enums.OrderStatus;
import com.immunopass.enums.VoucherStatus;
import com.immunopass.model.Order;
import com.immunopass.model.Voucher;
import com.immunopass.repository.OrderRepository;
import com.immunopass.restclient.SMSService;
import com.immunopass.utils.S3Util;
import com.immunopass.utils.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.stream.Collectors;

@Service
public class OrderService implements OrderController {
    private final OrderRepository orderRepository;
    private final VoucherService voucherService;
    private final SMSService smsService;
    private final S3Util s3Utill;

    public OrderService(OrderRepository orderRepository,
                        VoucherService voucherService,
                        SMSService smsService,
                        S3Util s3Utill) {
        this.orderRepository = orderRepository;
        this.voucherService = voucherService;
        this.smsService = smsService;
        this.s3Utill = s3Utill;
    }

    public void uploadOrder(MultipartFile file, Long createdBy) throws IOException {
        List<String> lines = FileUtil.getFileLines(file);
        String orderUUID = UUID.randomUUID().toString();

        // validate lines
        for (String line : lines.subList(1, lines.size())) {
            // name, mobileNumber, idType, idNumber
            String[] cols = line.split(",");
            checkLength(checkEmptyOrNull(cleanName(cols[0])), 40);
            cleanMobileNumber(cols[1]);
            IDType.valueOf(cols[2]);
            checkLength(cols[3], 40);
            checkLength(cols[4], 40);
        }
        URL s3URL = s3Utill.uploadDocumentSync(file.getInputStream(), "text/csv", null, orderUUID + "_order_file.csv");
        orderRepository.save(OrderEntity.builder()
                .status(OrderStatus.CREATED)
                .uploadedFile(s3URL.toString())
                .voucherCount(lines.size())
                .uploadedFile(s3URL.toString())
                .createdBy(createdBy)
                .build()
        );
    }

    public List<Order> getOrdersHavingStatus(OrderStatus status) {
        return orderRepository.getOrdersHavingStatus(status.toString())
                .stream()
                .map(this::mapEntityToModel)
                .collect(Collectors.toList());
    }

    public void createVouchers(Order order) throws IOException {
        List<String> lines = s3Utill.getFileLinesWithoutHeader(order.getUploadedFile());

        for (String line : lines) {
            // name, mobileNumber, idType, govtIDNumber, empID
            String[] cols = line.split(",");
            String name = checkLength(checkEmptyOrNull(cleanName(cols[0])), 40);
            String phoneNumber = cleanMobileNumber(cols[1]);
            IDType idType = IDType.valueOf(cols[2]);
            String govtIDNumber = checkLength(cols[3], 40);
            String empID = checkLength(cols[4], 40);
            voucherService.createVoucher(Voucher.builder()
                    .voucherCode(FileUtil.getRandomString(8))
                    .userGovtIDType(idType)
                    .issuerId(order.getCreatedBy())
                    .orderId(order.getId())
                    .status(VoucherStatus.CREATED)
                    .userEmpId(empID)
                    .userGovernmentId(govtIDNumber)
                    .userName(name)
                    .userMobile(phoneNumber)
                    .build()
            );
        }
        voucherService.updateVoucherStatusForOrder(order.getId(), VoucherStatus.ALLOTTED);

        orderRepository.updateOrderStatus(OrderStatus.PROCESSING.name(), order.getId());
    }

    public void processOrder(Order order) throws IOException {
        List<Voucher> vouchers = voucherService.getVouchersByOrderID(order.getId());
        boolean failure = false;
        for (Voucher voucher : vouchers) {
            if (voucher.getStatus() == VoucherStatus.ALLOTTED) {
                try {
                    if (smsService.sendVoucherSMS(voucher)) {
                        voucherService.updateVoucherStatus(voucher.getId(), VoucherStatus.PROCESSED);
                    } else {
                        failure = true;
                        voucherService.increaseRetryCount(voucher.getId(), "Failed to send sms");
                    }
                } catch (Exception e) {
                    failure = true;
                    voucherService.increaseRetryCount(voucher.getId(), e.getLocalizedMessage());
                }
            }
        }
        if (!failure) {
            orderRepository.updateOrderStatus(OrderStatus.PROCESSED.name(), order.getId());
        }
    }

    public Order mapEntityToModel(OrderEntity entity) {
        return Order.builder()
                .uploadedFile(entity.getUploadedFile())
                .status(entity.getStatus())
                .voucherCount(entity.getVoucherCount())
                .createdBy(entity.getCreatedBy())
                .id(entity.getId())
                .build();
    }

    @Override
    public void createOrder(MultipartFile file) {
        try {
            uploadOrder(file, 4L); // fixme: get accountID from accessToken
        } catch (IOException e) {
            throw new RuntimeException("Error in uploading file " + e.getLocalizedMessage());
        }
    }

    @Override
    public void createVouchers(Long id) {
        // fixme: get accountID from accessToken
        OrderEntity order = orderRepository.getOrder(id);
        try {
            createVouchers(mapEntityToModel(order));
        } catch (IOException e) {
            throw new RuntimeException("Error in creating vouchers " + e.getLocalizedMessage());
        }
    }

    @Override
    public void processOrder(Long id) {
        // fixme: get accountID from accessToken
        OrderEntity order = orderRepository.getOrder(id);
        try {
            processOrder(mapEntityToModel(order));
        } catch (IOException e) {
            throw new RuntimeException("Error in processing vouchers " + e.getLocalizedMessage());
        }
    }
}
