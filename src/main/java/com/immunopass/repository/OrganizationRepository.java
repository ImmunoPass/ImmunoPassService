package com.immunopass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import com.immunopass.entity.OrganizationEntity;


public interface OrganizationRepository extends JpaRepository<OrganizationEntity, Long> {
    @Modifying
    @Transactional
    @Query(value = "update immunopass.organization set redeemed_vouchers = redeemed_vouchers + 1"
            + " where id = :organizationId", nativeQuery = true)
    void increaseRedeemedVoucherCount(Long organizationId);
}
