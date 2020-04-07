package com.immunopass.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.immunopass.controller.VoucherController;
import com.immunopass.entity.VoucherEntity;
import com.immunopass.enums.VoucherStatus;
import com.immunopass.model.Voucher;
import com.immunopass.repository.VoucherRepository;

import javax.validation.Valid;
import java.util.List;


@Service
public class VoucherService implements VoucherController {

    @Autowired
    private VoucherRepository voucherRepository;

    public Voucher createVoucher(final Voucher voucher) {
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

    @Override
    public void claim(@Valid Long id) {
        voucherRepository.updateVoucherStatus(VoucherStatus.REDEEMED, id);
    }


    public List<Voucher> getVouchersByOrderID(Long orderID) {
        return voucherRepository.getVouchersForOrder(orderID);
    }


    public void updateVoucherStatusForOrder(Long orderID, VoucherStatus voucherStatus) {
        voucherRepository.updateVoucherStatusForOrder(voucherStatus, orderID);
    }

    public void updateVoucherStatus(Long voucherID, VoucherStatus voucherStatus) {
        voucherRepository.updateVoucherStatusForOrder(voucherStatus, voucherID);
    }

    public void increaseRetryCount(Long voucherID, String reason) {
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
}
