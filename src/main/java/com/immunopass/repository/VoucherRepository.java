package com.immunopass.repository;

import com.immunopass.entity.VoucherEntity;
import com.immunopass.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


public interface VoucherRepository extends JpaRepository<VoucherEntity, Long> {
    @Modifying
    @Query(value = "update immunopass.voucher set status = :status where order_id = :orderID", nativeQuery = true)
    @Transactional
    void updateVoucherStatusForOrder(String status, Long orderID);

    @Query(value = "SELECT * FROM immunopass.voucher WHERE order_id = :orderID", nativeQuery = true)
    List<VoucherEntity> getVouchersForOrder(Long orderID);


    @Modifying
    @Query(value = "update immunopass.voucher set status = :status where id = :voucherID", nativeQuery = true)
    @Transactional
    void updateVoucherStatus(String status, Long voucherID);

    @Modifying
    @Query(value = "update immunopass.voucher set retry_count = retry_count+1, last_failure_reason=:lastFailureReason where id = :voucherID", nativeQuery = true)
    @Transactional
    void increaseRetryCount(Long voucherID, String lastFailureReason);

    @Query(value = "SELECT * FROM immunopass.voucher WHERE voucher_code = :voucherCode", nativeQuery = true)
    VoucherEntity findByVoucherCode(String voucherCode);

}
