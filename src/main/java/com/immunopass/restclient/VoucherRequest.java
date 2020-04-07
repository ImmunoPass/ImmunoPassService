package com.immunopass.restclient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown=true)
@Getter
@Setter
@Builder
public class VoucherRequest {
    private String to;
    private String userName;
    private String userDOB;
    private String voucherCode;
    private String userMobileNumber;
}
