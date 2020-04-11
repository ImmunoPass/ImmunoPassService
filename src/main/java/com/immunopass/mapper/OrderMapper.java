package com.immunopass.mapper;

import com.immunopass.entity.OrderEntity;
import com.immunopass.model.Order;
import lombok.experimental.UtilityClass;


@UtilityClass
public class OrderMapper {
    public Order map(OrderEntity orderEntity) {
        return Order.builder()
                .uploadedFile(orderEntity.getUploadedFile())
                .status(orderEntity.getStatus())
                .voucherCount(orderEntity.getVoucherCount())
                .createdBy(orderEntity.getCreatedBy())
                .id(orderEntity.getId())
                .build();
    }
}
