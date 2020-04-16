package com.immunopass.model;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.immunopass.enums.OrderStatus;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VoucherOrder {
    private final Long id;
    private final Integer voucherCount;
    private final String uploadedFile;
    private final OrderStatus status;
    private final Long createdAccountId;
    private final Long createdOrganizationId;
    private final LocalDateTime createdAt;
}
