package com.immunopass.repository;

import com.immunopass.entity.OrderEntity;
import com.immunopass.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Modifying
    @Query(value = "update order set status = :status where order_id = :orderID", nativeQuery = true)
    void updateOrderStatus(OrderStatus status, Long orderID);

    @Query(value = "SELECT * FROM order WHERE id = :orderID", nativeQuery = true)
    OrderEntity getOrder(Long orderID);
}
