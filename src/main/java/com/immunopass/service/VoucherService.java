package com.immunopass.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.immunopass.controller.VoucherController;
import com.immunopass.entity.VoucherEntity;
import com.immunopass.enums.VoucherStatus;
import com.immunopass.model.Voucher;
import com.immunopass.repository.VoucherRepository;


@Service
public class VoucherService implements VoucherController {

    @Autowired
    private VoucherRepository voucherRepository;

    @Override public Voucher createVoucher(final Voucher voucher) {
        VoucherEntity voucherEntity =
                VoucherEntity.builder()
                        .voucherCode("RANDOM_CODE")
                        .issuerId(voucher.getIssuerId())
                        .userName(voucher.getUserName())
                        .userMobile(voucher.getUserMobile())
                        .userEmpId(voucher.getUserEmpId())
                        .userGovernmentId(voucher.getUserGovernmentId())
                        .userLocation(voucher.getUserLocation())
                        .status(VoucherStatus.ALLOTTED)
                        .orderId(voucher.getOrderId())
                        .build();
        voucherEntity = voucherRepository.save(voucherEntity);
        return mapEntityToModel(voucherEntity);
    }

    @Override
    public Voucher getVoucher(Long id) {
        return voucherRepository
                .findById(id)
                .map(this::mapEntityToModel)
                .orElse(null);
    }

    @Override
    public void processVoucher(Long id, String action) {
        System.out.println("Performing action " + action + " on voucher = " + id);
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
                .userLocation(voucherEntity.getUserLocation())
                .status(voucherEntity.getStatus())
                .orderId(voucherEntity.getIssuerId())
                .build();
    }

}
