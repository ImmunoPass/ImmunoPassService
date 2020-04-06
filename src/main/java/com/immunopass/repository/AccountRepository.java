package com.immunopass.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.immunopass.entity.AccountEntity;
import com.immunopass.enums.IdentifierType;


public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    Optional<AccountEntity> findByIdentifierAndIdentifierType(String identifier,
            IdentifierType identifierType);
}
