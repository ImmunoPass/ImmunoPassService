package com.immunopass.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.immunopass.entity.OtpEntity;
import com.immunopass.enums.IdentifierType;


public interface OtpRepository extends JpaRepository<OtpEntity, Long> {
    Optional<OtpEntity> findByIdentifierAndIdentifierType(String identifier,
                                                              IdentifierType identifierType);

    Optional<OtpEntity> findFirstByIdentifierOrderByCreatedAtDesc(String identifier);

}
