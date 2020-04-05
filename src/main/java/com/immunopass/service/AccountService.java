package com.immunopass.service;

import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.immunopass.controller.AccountController;
import com.immunopass.entity.AccountEntity;
import com.immunopass.enums.AccountIdentifierType;
import com.immunopass.enums.EntityStatus;
import com.immunopass.model.Account;
import com.immunopass.repository.AccountRepository;


@Service
public class AccountService implements AccountController {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account createAccount(@NotNull final Account account) {
        return accountRepository.findByIdentifierAndIdentifierType(account.getIdentifier(), account.getIdentifierType())
                .map(this::mapEntityToModel)
                .orElseGet(() -> {
                    AccountEntity accountEntity =
                            AccountEntity.builder()
                                    .name(account.getName())
                                    .identifier(account.getIdentifier())
                                    .identifierType(account.getIdentifierType())
                                    .organizationId(account.getOrganizationId())
                                    .organizationType(account.getOrganizationType())
                                    .status(EntityStatus.ACTIVE)
                                    .build();
                    accountEntity = accountRepository.save(accountEntity);
                    return mapEntityToModel(accountEntity);
                });
    }

    public Account fetchAccountByIdentifierAndIdentifierType(String identifier, AccountIdentifierType identifierType) {
        return accountRepository
                .findByIdentifierAndIdentifierType(identifier, identifierType)
                .map(this::mapEntityToModel)
                .orElse(null);
    }

    private Account mapEntityToModel(AccountEntity accountEntity) {
        return Account.builder()
                .id(accountEntity.getId())
                .name(accountEntity.getName())
                .identifier(accountEntity.getIdentifier())
                .identifierType(accountEntity.getIdentifierType())
                .organizationId(accountEntity.getOrganizationId())
                .organizationType(accountEntity.getOrganizationType())
                .status(accountEntity.getStatus())
                .build();
    }
}
