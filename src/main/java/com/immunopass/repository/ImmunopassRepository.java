package com.immunopass.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.immunopass.entity.ImmunopassEntity;


public interface ImmunopassRepository extends JpaRepository<ImmunopassEntity, Long> {

    Optional<ImmunopassEntity> findByUserMobile(String userMobile);

    Optional<ImmunopassEntity> findByImmunopassCode(String ImmunopassCode);

}
