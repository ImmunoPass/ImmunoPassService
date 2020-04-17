package com.immunopass.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.immunopass.enums.IdentifierType;
import com.immunopass.enums.OtpStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SendOtpResponse {
    private final String validTill;
    private final String identifier;
    private final IdentifierType identifierType;
    private final Integer retryCount;
    private final OtpStatus status;
}
