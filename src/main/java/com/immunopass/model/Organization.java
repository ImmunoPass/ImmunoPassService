package com.immunopass.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Organization {
    private final Long id;
    private final String name;
    private final OrganizationType type; // TODO: change from string to enum
    private final EntityStatus status; // TODO: change from string to enum
    private final Integer totalVouchers;
    private final Integer usedVouchers;
}
