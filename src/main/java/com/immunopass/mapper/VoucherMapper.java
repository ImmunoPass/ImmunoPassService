package com.immunopass.mapper;

import com.immunopass.entity.VoucherEntity;
import com.immunopass.model.Voucher;
import lombok.experimental.UtilityClass;


@UtilityClass
public class VoucherMapper {
    public Voucher map(VoucherEntity voucherEntity) {
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
                .retryCount(voucherEntity.getRetryCount())
                .lastFailureReason(voucherEntity.getLastFailureReason())
                .build();
    }
}
