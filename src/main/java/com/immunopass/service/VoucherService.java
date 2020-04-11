package com.immunopass.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.immunopass.controller.VoucherController;
import com.immunopass.entity.VoucherEntity;
import com.immunopass.enums.VoucherStatus;
import com.immunopass.mapper.VoucherMapper;
import com.immunopass.model.Voucher;
import com.immunopass.model.VoucherRequest;
import com.immunopass.repository.VoucherRepository;


@Service
public class VoucherService implements VoucherController {

    @Autowired
    private VoucherRepository voucherRepository;

    Voucher createVoucher(final Voucher voucher) {
        VoucherEntity voucherEntity =
                VoucherEntity.builder()
                        .voucherCode(voucher.getVoucherCode())
                        .issuerId(voucher.getIssuerId())
                        .userName(voucher.getUserName())
                        .userMobile(voucher.getUserMobile())
                        .userEmpId(voucher.getUserEmpId())
                        .userGovernmentId(voucher.getUserGovernmentId())
                        .userLocation(voucher.getUserLocation())
                        .status(voucher.getStatus())
                        .orderId(voucher.getOrderId())
                        .userGovtIdType(voucher.getUserGovtIDType())
                        .retryCount(0L)
                        .build();
        voucherEntity = voucherRepository.save(voucherEntity);
        return VoucherMapper.map(voucherEntity);
    }


    List<Voucher> getVouchersByOrderID(Long orderID) {
        return voucherRepository.getVouchersForOrder(orderID)
                .stream()
                .map(VoucherMapper::map)
                .collect(Collectors.toList());
    }


    void updateVoucherStatusForOrder(Long orderID, VoucherStatus voucherStatus) {
        voucherRepository.updateVoucherStatusForOrder(voucherStatus.name(), orderID);
    }

    void updateVoucherStatus(Long voucherID, VoucherStatus voucherStatus) {
        voucherRepository.updateVoucherStatus(voucherStatus.name(), voucherID);
    }

    void increaseRetryCount(Long voucherID, String reason) {
        voucherRepository.increaseRetryCount(voucherID, reason);
    }

    @Override
    public void claimVoucher(@Valid VoucherRequest voucherRequest) {
        String voucherCode = voucherRequest.getVoucherCode();
        VoucherEntity voucher = voucherRepository.findByVoucherCode(voucherCode);
        if (voucher==null || voucher.getStatus() != VoucherStatus.PROCESSED) {
            throw new RuntimeException("No available voucher for the code");
        }
        voucherRepository.updateVoucherStatus(VoucherStatus.REDEEMED.name(), voucher.getId());
    }

    @Override
    public Voucher getVoucher(@Valid VoucherRequest voucherRequest) {
        String voucherCode = voucherRequest.getVoucherCode();
        VoucherEntity voucher = voucherRepository.findByVoucherCode(voucherCode);
        if(voucher == null) {
            throw new RuntimeException("Voucher not found");
        }
        return VoucherMapper.map(voucher);
    }

    @Override
    public List<Voucher> getVouchers() {
        return  voucherRepository.findAll()
                .stream()
                .map(VoucherMapper::map)
                .collect(Collectors.toList());
    }
}
