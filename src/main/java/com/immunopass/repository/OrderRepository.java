package com.immunopass.repository;

import com.immunopass.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    @Modifying
    @Query(value = "update immunopass.order set status = :status where id = :orderID", nativeQuery = true)
    @Transactional
    void updateOrderStatus(String status, Long orderID);

    @Query(value = "SELECT * FROM immunopass.order WHERE id = :orderID", nativeQuery = true)
    OrderEntity getOrder(Long orderID);

    @Query(value = "SELECT * FROM immunopass.order WHERE status = :status", nativeQuery = true)
    List<OrderEntity> getOrdersHavingStatus(String status);
}
