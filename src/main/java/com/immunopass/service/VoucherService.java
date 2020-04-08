package com.immunopass.service;

import com.immunopass.controller.VoucherController;
import com.immunopass.entity.VoucherEntity;
import com.immunopass.enums.VoucherStatus;
import com.immunopass.model.Voucher;
import com.immunopass.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


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
                        .userGovtIDType(voucher.getUserGovtIDType())
                        .build();
        voucherEntity = voucherRepository.save(voucherEntity);
        return mapEntityToModel(voucherEntity);
    }


    List<Voucher> getVouchersByOrderID(Long orderID) {
        return voucherRepository.getVouchersForOrder(orderID);
    }


    void updateVoucherStatusForOrder(Long orderID, VoucherStatus voucherStatus) {
        voucherRepository.updateVoucherStatusForOrder(voucherStatus, orderID);
    }

    void updateVoucherStatus(Long voucherID, VoucherStatus voucherStatus) {
        voucherRepository.updateVoucherStatusForOrder(voucherStatus, voucherID);
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
                .userGovtIDType(voucherEntity.getUserGovtIDType())
                .userLocation(voucherEntity.getUserLocation())
                .status(voucherEntity.getStatus())
                .orderId(voucherEntity.getIssuerId())
                .build();
    }

    @Override
    public void claimVoucher(@Valid String voucherCode) {
        Optional<VoucherEntity> voucher = voucherRepository.findByVoucherCode(voucherCode);
        if (!voucher.isPresent() || voucher.get().getStatus() != VoucherStatus.ALLOTTED) {
            throw new RuntimeException("No available voucher for the code");
        }
        voucherRepository.updateVoucherStatus(VoucherStatus.REDEEMED, voucher.get().getId());
    }

    @Override
    public Voucher getVoucher(@Valid String voucherCode) {

        Optional<VoucherEntity> voucher = voucherRepository.findByVoucherCode(voucherCode);
        if (!voucher.isPresent()) {
            throw new RuntimeException("No voucher for the code");
        }

        return mapEntityToModel(voucher.get());
    }
}
