package com.immunopass.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.immunopass.entity.OtpEntity;


public interface OtpRepository extends JpaRepository<OtpEntity, Long> {

    Optional<OtpEntity> findFirstByIdentifierOrderByCreatedAtDesc(String identifier);

}
