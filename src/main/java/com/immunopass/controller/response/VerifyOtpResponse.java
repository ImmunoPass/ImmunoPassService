package com.immunopass.controller.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VerifyOtpResponse {
    private String accessToken;
}
