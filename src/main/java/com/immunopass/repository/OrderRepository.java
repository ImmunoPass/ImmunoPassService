package com.immunopass.repository;

import com.immunopass.entity.OrderEntity;
import com.immunopass.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Modifying
    @Query(value = "update immunopass.order set status = :status where id = :orderID", nativeQuery = true)
    @Transactional
    void updateOrderStatus(String status, Long orderID);

    @Query(value = "SELECT * FROM immunopass.order WHERE id = :orderID", nativeQuery = true)
    OrderEntity getOrder(Long orderID);
}
