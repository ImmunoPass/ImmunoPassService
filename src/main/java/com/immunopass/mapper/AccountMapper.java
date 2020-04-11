package com.immunopass.mapper;

import com.immunopass.entity.AccountEntity;
import com.immunopass.model.Account;
import lombok.experimental.UtilityClass;


@UtilityClass
public class AccountMapper {
    public Account map(AccountEntity accountEntity) {
        return Account.builder()
                .id(accountEntity.getId())
                .name(accountEntity.getName())
                .identifier(accountEntity.getIdentifier())
                .identifierType(accountEntity.getIdentifierType())
                .organizationId(accountEntity.getOrganizationId())
                .pathologyLabId(accountEntity.getPathologyLabId())
                .status(accountEntity.getStatus())
                .build();
    }
}
