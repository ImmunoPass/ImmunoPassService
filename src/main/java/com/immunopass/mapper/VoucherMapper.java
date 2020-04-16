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
                .issuerAccountId(voucherEntity.getIssuerAccountId())
                .issuerOrganizationId(voucherEntity.getIssuerOrganizationId())
                .userName(voucherEntity.getUserName())
                .userMobile(voucherEntity.getUserMobile())
                .userEmpId(voucherEntity.getUserEmpId())
                .userGovernmentId(voucherEntity.getUserGovernmentId())
                .userGovtIDType(voucherEntity.getUserGovtIdType())
                .userLocation(voucherEntity.getUserLocation())
                .status(voucherEntity.getStatus())
                .redeemedAccountId(voucherEntity.getRedeemedAccountId())
                .redeemedPathologyLabId(voucherEntity.getRedeemedPathologyLabId())
                .orderId(voucherEntity.getIssuerAccountId())
                .build();
    }
}
