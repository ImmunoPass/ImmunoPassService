package com.immunopass.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.immunopass.enums.ImmunoTestResult;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@NotNull
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
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
