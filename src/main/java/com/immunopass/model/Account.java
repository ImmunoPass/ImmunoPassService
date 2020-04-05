package com.immunopass.model;

import com.immunopass.model.enums.AccountIdentifierType;
import com.immunopass.model.enums.EntityStatus;
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
