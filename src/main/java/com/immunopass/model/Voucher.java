package com.immunopass.model;

import javax.validation.constraints.NotNull;
import com.immunopass.enums.VoucherStatus;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@NotNull
public class Voucher {
    private final Long id;
    private final String voucherCode;
    @NotNull
    private final Long issuerId;
    @NotNull
    private final String userName;
    @NotNull
    private final String userMobile;
    private final String userEmpId;
    private final String userGovernmentId;
    private final String userLocation;
    private final VoucherStatus status;
    @NotNull
    private final Long orderId;
    private final Long immunopassId;
}
