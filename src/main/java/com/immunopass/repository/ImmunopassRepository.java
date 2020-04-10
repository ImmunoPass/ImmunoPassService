package com.immunopass.repository;

import com.immunopass.entity.ImmunopassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ImmunopassRepository extends JpaRepository<ImmunopassEntity, Long> {

    Optional<ImmunopassEntity> findByUserMobile(String userMobile);

    Optional<ImmunopassEntity> findByImmunopassCode(String ImmunopassCode);

}
