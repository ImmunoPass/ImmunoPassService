package com.immunopass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.immunopass.entity.OrganizationEntity;


public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Long> {
}
