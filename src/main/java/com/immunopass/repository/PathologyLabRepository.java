package com.immunopass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.immunopass.entity.PathologyLabEntity;


public interface PathologyLabRepository extends JpaRepository<PathologyLabEntity, Long> {
}
