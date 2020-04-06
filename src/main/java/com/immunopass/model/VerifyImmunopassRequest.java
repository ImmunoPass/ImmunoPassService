package com.immunopass.model;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@NotNull
public class VerifyImmunopassRequest {
    @NotNull
    private final String userMobile;
    @NotNull
    private final String immunopassCode;
}
