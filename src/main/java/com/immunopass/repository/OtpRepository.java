package com.immunopass.repository;

import com.immunopass.entity.OtpEntity;
import com.immunopass.enums.IdentifierType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface OtpRepository extends JpaRepository<OtpEntity, Long> {
    Optional<OtpEntity> findByIdentifierAndIdentifierType(String identifier,
                                                              IdentifierType identifierType);

    Optional<OtpEntity> findByIdentifier(String identifier);
}
