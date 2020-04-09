package com.immunopass.service;

import com.immunopass.controller.VoucherController;
import com.immunopass.entity.VoucherEntity;
import com.immunopass.enums.VoucherStatus;
import com.immunopass.model.Voucher;
import com.immunopass.model.VoucherRequest;
import com.immunopass.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


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
                        .build();
        voucherEntity = voucherRepository.save(voucherEntity);
        return mapEntityToModel(voucherEntity);
    }


    List<Voucher> getVouchersByOrderID(Long orderID) {
        return voucherRepository.getVouchersForOrder(orderID)
                .stream()
                .map(this::mapEntityToModel)
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

    private Voucher mapEntityToModel(VoucherEntity voucherEntity) {
        return Voucher.builder()
                .id(voucherEntity.getId())
                .voucherCode(voucherEntity.getVoucherCode())
                .issuerId(voucherEntity.getIssuerId())
                .userName(voucherEntity.getUserName())
                .userMobile(voucherEntity.getUserMobile())
                .userEmpId(voucherEntity.getUserEmpId())
                .userGovernmentId(voucherEntity.getUserGovernmentId())
                .userGovtIDType(voucherEntity.getUserGovtIdType())
                .userLocation(voucherEntity.getUserLocation())
                .status(voucherEntity.getStatus())
                .orderId(voucherEntity.getIssuerId())
                .build();
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
        return mapEntityToModel(voucher);
    }

    @Override
    public List<Voucher> getVouchers() {
        return  voucherRepository.findAll()
                .stream()
                .map(this::mapEntityToModel)
                .collect(Collectors.toList());
    }
}
