package com.immunopass.model;

import com.immunopass.enums.EntityStatus;
import com.immunopass.enums.IdentifierType;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Builder
@NotNull
public class Account {

    private final Long id;

    @NotBlank
    private final String name;

    @NotBlank
    private final String identifier;

    @NotNull
    private final IdentifierType identifierType;

    private final String passwordHash;

    private final Long pathologyLabId;

    private final Long organizationId;

    private final EntityStatus status;
}
