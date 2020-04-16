package com.immunopass.model;

import java.time.LocalDateTime;
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
public class Otp {
    private final Long id;
    private final LocalDateTime validTill;
    private final String otp;
    private final String identifier;
    private final IdentifierType identifierType;
    private final Integer retryCount;
    private final Integer verificationAttempts;
    private final OtpStatus status;
}
