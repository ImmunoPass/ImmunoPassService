package com.immunopass.model;

import javax.validation.constraints.NotNull;
import com.immunopass.enums.EntityStatus;
import com.immunopass.enums.IdentifierType;
import com.immunopass.enums.OrganizationType;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@NotNull
public class Account {

    private final Long id;

    @NotNull
    private final String name;

    @NotNull
    private final String identifier;

    @NotNull
    private final IdentifierType identifierType;

    private final String passwordHash;

    @NotNull
    private final Long organizationId;

    @NotNull
    private final OrganizationType organizationType;

    private final EntityStatus status;
}
