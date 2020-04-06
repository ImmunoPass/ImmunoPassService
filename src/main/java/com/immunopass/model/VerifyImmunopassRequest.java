package com.immunopass.model;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@NotNull
public class VerifyImmunopassRequest {
    private final String userMobile;
    private final String immunopassCode;
}
