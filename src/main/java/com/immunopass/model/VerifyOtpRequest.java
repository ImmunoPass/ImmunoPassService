package com.immunopass.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@NotNull
public class VerifyOtpRequest {
    @NotBlank
    private String otp;
    @NotBlank
    private String identifier;
}
