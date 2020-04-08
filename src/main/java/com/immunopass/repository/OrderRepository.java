package com.immunopass.repository;

import com.immunopass.entity.OrderEntity;
import com.immunopass.enums.OrderStatus;
import com.immunopass.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Modifying
    @Query("update order set status = :status where order_id = :orderID")
    void updateOrderStatus(OrderStatus status, Long orderID);

    @Query("SELECT * FROM order WHERE id = :orderID")
    Order getOrder(Long orderID);
}
