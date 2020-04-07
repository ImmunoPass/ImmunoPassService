package com.immunopass.model;

import com.immunopass.enums.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
@Builder
public class Order {
    private final Long id;
    private final Integer voucherCount;
    private final String uploadedFile;
    private final OrderStatus status;
    private final Long createdBy;
    private final String uuid;
    private LocalDateTime createdAt;
}
