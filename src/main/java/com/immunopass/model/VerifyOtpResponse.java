package com.immunopass.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VerifyOtpResponse {
    private String accessToken;
}
