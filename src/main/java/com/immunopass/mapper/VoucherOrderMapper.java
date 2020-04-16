package com.immunopass.mapper;

import com.immunopass.entity.VoucherOrderEntity;
import com.immunopass.model.VoucherOrder;
import lombok.experimental.UtilityClass;


@UtilityClass
public class VoucherOrderMapper {
    public VoucherOrder map(VoucherOrderEntity voucherOrderEntity) {
        return VoucherOrder.builder()
                .id(voucherOrderEntity.getId())
                .uploadedFile(voucherOrderEntity.getUploadedFile())
                .status(voucherOrderEntity.getStatus())
                .voucherCount(voucherOrderEntity.getVoucherCount())
                .createdAccountId(voucherOrderEntity.getCreatedAccountId())
                .createdOrganizationId(voucherOrderEntity.getCreatedOrganizationId())
                .createdAt(voucherOrderEntity.getCreatedAt())
                .build();
    }
}
