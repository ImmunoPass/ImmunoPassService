package com.immunopass.model;

import com.immunopass.enums.IdentifierType;
import com.immunopass.enums.EntityStatus;
import com.immunopass.enums.OrganizationType;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class Account {
    private final Long id;
    private final String name;
    private final String identifier;
    private final IdentifierType identifierType;
    private final String passwordHash;
    private final Long organizationId;
    private final OrganizationType organizationType;
    private final EntityStatus status;
}
