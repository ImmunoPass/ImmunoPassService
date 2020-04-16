package com.immunopass.model;

import javax.validation.constraints.NotNull;
import com.immunopass.enums.IDType;
import com.immunopass.enums.VoucherStatus;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@NotNull
public class Voucher {
    private final Long id;
    private final String voucherCode;
    private final Long issuerAccountId;
    private final Long issuerOrganizationId;
    private final String userName;
    private final String userMobile;
    private final String userEmpId;
    private final String userGovernmentId;
    private final IDType userGovtIDType;
    private final String userLocation;
    private final VoucherStatus status;
    private final Long redeemedAccountId;
    private final Long redeemedPathologyLabId;
    private final Long orderId;
    private final Long immunopassId;
    private final Long retryCount;
    private final String lastFailureReason;
}
