package com.immunopass.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Immunopass {
    private final Long id;
    private final String userName;
    private final String userMobile;
    private final String userEmpId;
    private final String userGovernmentId;
    private final String userLocation;
    private final String immunoTestResult; // TODO: string to enum
    private final String immunopassCode;

}
