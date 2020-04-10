package com.immunopass.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.immunopass.controller.AccountController;
import com.immunopass.entity.AccountEntity;
import com.immunopass.enums.IdentifierType;
import com.immunopass.enums.EntityStatus;
import com.immunopass.model.Account;
import com.immunopass.repository.AccountRepository;


@Service
public class AccountService implements AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account createAccount(final Account account) {
        return accountRepository.findByIdentifierAndIdentifierType(account.getIdentifier(), account.getIdentifierType())
                .map(this::mapEntityToModel)
                .orElseGet(() -> {
                    AccountEntity accountEntity =
                            AccountEntity.builder()
                                    .name(account.getName())
                                    .identifier(account.getIdentifier())
                                    .pathologyLabId(account.getPathologyLabId())
                                    .identifierType(account.getIdentifierType())
                                    .organizationId(account.getOrganizationId())
                                    .status(EntityStatus.ACTIVE)
                                    .build();
                    accountEntity = accountRepository.save(accountEntity);
                    return mapEntityToModel(accountEntity);
                });
    }

    public Account fetchAccountByIdentifierAndIdentifierType(String identifier, IdentifierType identifierType) {
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
                .pathologyLabId(accountEntity.getPathologyLabId())
                .identifierType(accountEntity.getIdentifierType())
                .organizationId(accountEntity.getOrganizationId())
                .status(accountEntity.getStatus())
                .build();
    }
}
