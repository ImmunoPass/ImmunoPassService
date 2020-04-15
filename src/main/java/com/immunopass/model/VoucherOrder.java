package com.immunopass.model;

import java.time.LocalDateTime;
import com.immunopass.enums.OrderStatus;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class VoucherOrder {
    private final Long id;
    private final Integer voucherCount;
    private final String uploadedFile;
    private final OrderStatus status;
    private final Long createdBy;
    private final LocalDateTime createdAt;
}
