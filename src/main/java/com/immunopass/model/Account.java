package com.immunopass.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Account {
    private final Long id;
    private final String name;
    private final String identifier;
    private final AccountIdentifierType identifierType;
    private final String passwordHash;
    private final Long organizationId;
    private final String organizationType;
    private final EntityStatus status;
}
