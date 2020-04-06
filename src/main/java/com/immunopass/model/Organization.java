package com.immunopass.model;

import javax.validation.constraints.NotNull;
import com.immunopass.enums.EntityStatus;
import com.immunopass.enums.OrganizationType;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@NotNull
public class Organization {
    private final Long id;
    @NotNull
    private final String name;
    @NotNull
    private final OrganizationType type;
    private final EntityStatus status;
    @NotNull
    private final Integer totalVouchers;
    private final Integer usedVouchers;
}
