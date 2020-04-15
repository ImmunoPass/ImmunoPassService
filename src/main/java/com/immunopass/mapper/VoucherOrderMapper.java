package com.immunopass.mapper;

import com.immunopass.entity.VoucherOrderEntity;
import com.immunopass.model.VoucherOrder;
import lombok.experimental.UtilityClass;


@UtilityClass
public class VoucherOrderMapper {
    public VoucherOrder map(VoucherOrderEntity voucherOrderEntity) {
        return VoucherOrder.builder()
                .uploadedFile(voucherOrderEntity.getUploadedFile())
                .status(voucherOrderEntity.getStatus())
                .voucherCount(voucherOrderEntity.getVoucherCount())
                .createdBy(voucherOrderEntity.getCreatedBy())
                .id(voucherOrderEntity.getId())
                .build();
    }
}
