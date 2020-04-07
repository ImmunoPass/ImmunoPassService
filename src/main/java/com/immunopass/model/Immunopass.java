package com.immunopass.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.immunopass.enums.ImmunoTestResult;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@NotNull
public class Immunopass {
    private final Long id;
    @NotBlank
    private final String userName;
    @NotBlank
    private final String userMobile;
    private final String userEmpId;
    private final String userGovernmentId;
    private final String userLocation;
    private final ImmunoTestResult immunoTestResult;
    private final String immunopassCode;

}
