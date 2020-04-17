package com.immunopass.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.immunopass.enums.AccountType;
import com.immunopass.enums.IdentifierType;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@NotNull
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SendOtpRequest {
    @NotBlank(message = "Invalid value")
    private final String identifier;
    @NotNull
    private final IdentifierType identifierType;
    @NotNull
    private final AccountType accountType;
}
