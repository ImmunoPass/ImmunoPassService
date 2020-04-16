package com.immunopass.model;

import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.immunopass.enums.IDType;
import com.immunopass.enums.VoucherStatus;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@NotNull
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
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
}
