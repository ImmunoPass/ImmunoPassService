package com.immunopass.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.immunopass.entity.VoucherOrderEntity;
import com.immunopass.enums.OrderStatus;


public interface VoucherOrderRepository extends JpaRepository<VoucherOrderEntity, Long> {

    List<VoucherOrderEntity> findAllByStatus(OrderStatus orderStatus);

}
