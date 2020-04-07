package com.immunopass.repository;

import com.immunopass.entity.VoucherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface VoucherRepository extends JpaRepository<VoucherEntity, Long> {
    Optional<VoucherEntity> findByVoucherCode(String voucherCode);
}
