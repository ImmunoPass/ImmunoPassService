package com.immunopass.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Organization {
    private final Long id;
    private final String name;
    private final String type; // TODO: change from string to enum
    private final String status; // TODO: change from string to enum
    private final Integer total_vouchers;
    private final Integer used_vouchers;
}
