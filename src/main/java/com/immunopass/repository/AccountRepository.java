package com.immunopass.repository;

import com.immunopass.entity.AccountEntity;
import com.immunopass.enums.IdentifierType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByIdentifierAndIdentifierType(String identifier,
            IdentifierType identifierType);
}
