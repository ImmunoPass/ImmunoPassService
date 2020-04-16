package com.immunopass.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.immunopass.entity.VoucherEntity;


public interface VoucherRepository extends JpaRepository<VoucherEntity, Long> {

    List<VoucherEntity> findAllByOrderId(Long orderID);

    Optional<VoucherEntity> findByVoucherCode(String voucherCode);

}
