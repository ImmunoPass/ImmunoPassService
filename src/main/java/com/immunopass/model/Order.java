package com.immunopass.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Order {
    private final Long id;
    private final Integer voucherCount;
    private final String uploadedFile;
    private final OrderStatus status;
    private final Long createdBy;
}
