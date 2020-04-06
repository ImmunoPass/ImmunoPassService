package com.immunopass.controller.response;

import com.immunopass.enums.IdentifierType;
import com.immunopass.enums.OtpStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SendOtpResponse {
    private String validTill;
    private String identifier;
    private IdentifierType identifierType;
    private Integer tryCount;
    private OtpStatus status;
}
