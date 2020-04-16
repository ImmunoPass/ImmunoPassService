package com.immunopass.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.immunopass.enums.EntityStatus;
import com.immunopass.enums.OrganizationType;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@NotNull
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Organization {
    private final Long id;
    @NotBlank
    private final String name;
    @NotNull
    private final OrganizationType type;
    private final EntityStatus status;
    @NotNull
    private final Integer totalVouchers;
    private final Integer allotedVouchers;
    private final Integer redeemedVouchers;
}
