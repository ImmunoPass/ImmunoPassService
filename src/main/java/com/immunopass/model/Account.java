package com.immunopass.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.immunopass.enums.EntityStatus;
import com.immunopass.enums.IdentifierType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@NotNull
public class Account {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String identifier;

    @NotNull
    private IdentifierType identifierType;

    private Long organizationId;

    private Long pathologyLabId;

    private EntityStatus status;
}
