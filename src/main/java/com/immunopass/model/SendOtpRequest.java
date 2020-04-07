package com.immunopass.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.immunopass.enums.IdentifierType;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@NotNull
public class SendOtpRequest {
    @NotBlank(message = "Invalid value")
    private String identifier;
    @NotNull
    private IdentifierType identifierType;
}
