package com.immunopass.service;

import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.immunopass.controller.AccountController;
import com.immunopass.entity.AccountEntity;
import com.immunopass.enums.EntityStatus;
import com.immunopass.enums.ResourceType;
import com.immunopass.mapper.AccountMapper;
import com.immunopass.model.Account;
import com.immunopass.repository.AccountRepository;


@Service
public class AccountService implements AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    private final AccountRepository accountRepository;

    public AccountService(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override public Account getAccount(final @NotNull String id) {
        if (StringUtils.equals(ResourceType.CURRENT.toString(), id)) {
            Account account =
                    (Account) SecurityContextHolder
                            .getContext()
                            .getAuthentication()
                            .getPrincipal();
            return accountRepository
                    .findById(account.getId())
                    .filter(accountEntity -> accountEntity.getStatus() == EntityStatus.ACTIVE)
                    .map(AccountMapper::map)
                    .orElseThrow(() -> {
                        LOGGER.error("User account for the logged in user either doesn't exist in the system now or "
                                + "it's not ACTIVE.");
                        return new ResponseStatusException(HttpStatus.NOT_FOUND, "User account doesn't exist.");
                    });
        } else {
            LOGGER.error("Get Account API doesn't support non logged in user right now. Received request for the non "
                    + "logged in user.");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Account ID.");
        }
    }

    @Override
    public Account createAccount(final Account account) {
        return accountRepository.findByIdentifierAndIdentifierType(account.getIdentifier(), account.getIdentifierType())
                .map(accountEntity -> {
                    if (accountEntity.getStatus() == EntityStatus.ACTIVE) {
                        LOGGER.info("User account already exists in the system, returning same account.");
                        return accountEntity;
                    } else {
                        LOGGER.error("User account already exists in the system, but it's not ACTIVE.");
                        throw new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                "Account already exists, but the account isn't active.");
                    }
                })
                .map(AccountMapper::map)
                .orElseGet(() -> {
                    LOGGER.info("User account doesn't exists in the system, creating new account.");
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
                    return AccountMapper.map(accountEntity);
                });
    }
}
