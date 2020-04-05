package com.immunopass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.immunopass.entity.ImmunopassEntity;


public interface ImmunopassRepository extends JpaRepository<ImmunopassEntity, Long> {
}
