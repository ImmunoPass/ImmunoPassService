package com.immunopass.model;

import javax.validation.constraints.NotNull;
import com.immunopass.enums.ImmunoTestResult;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@NotNull
public class Immunopass {
    private final Long id;
    @NotNull
    private final String userName;
    @NotNull
    private final String userMobile;
    private final String userEmpId;
    private final String userGovernmentId;
    private final String userLocation;
    private final ImmunoTestResult immunoTestResult;
    private final String immunopassCode;

}
