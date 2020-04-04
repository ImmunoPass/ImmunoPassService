package com.immunopass.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Account {
    private final Long id;
    private final String name;
    private final String identifier;
    private final AccountIdentifierType identifierType; // TODO: change from string to enum
    private final String passwordHash;
    private final Long organizationId;
    private final String organizationType;
    private final String status; // TODO : change from string to enum
}
