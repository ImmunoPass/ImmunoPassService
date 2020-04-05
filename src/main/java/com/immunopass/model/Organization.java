package com.immunopass.model;

import com.immunopass.enums.EntityStatus;
import com.immunopass.enums.OrganizationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class Organization {
    private final Long id;
    private final String name;
    private final OrganizationType type;
    private final EntityStatus status;
    private final Integer totalVouchers;
    private final Integer usedVouchers;
}
