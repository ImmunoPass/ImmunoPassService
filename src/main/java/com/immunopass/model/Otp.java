package com.immunopass.model;

import java.time.LocalDateTime;
import com.immunopass.enums.IdentifierType;
import com.immunopass.enums.OtpStatus;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class Otp {
    private final Long id;
    private final LocalDateTime validTill;
    private final String otp;
    private final String identifier;
    private final IdentifierType identifierType;
    private final Integer tryCount;
    private final OtpStatus status;
}
