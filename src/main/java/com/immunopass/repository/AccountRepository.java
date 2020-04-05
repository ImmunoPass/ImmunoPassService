package com.immunopass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.immunopass.entity.AccountEntity;


public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
}
