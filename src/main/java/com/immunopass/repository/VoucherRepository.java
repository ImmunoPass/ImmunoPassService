package com.immunopass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.immunopass.entity.VoucherEntity;


public interface VoucherRepository extends JpaRepository<VoucherEntity, Long> {
}
