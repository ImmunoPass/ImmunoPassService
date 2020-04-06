package com.immunopass.controller.request;

import javax.validation.constraints.NotNull;
import com.immunopass.enums.IdentifierType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@NotNull
public class SendOtpRequest {
    @NotNull
    private String identifier;
    @NotNull
    private IdentifierType identifierType;
}
