package com.immunopass.repository;

import com.immunopass.entity.VoucherEntity;
import com.immunopass.enums.VoucherStatus;
import com.immunopass.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface VoucherRepository extends JpaRepository<VoucherEntity, Long> {
    @Modifying
    @Query("update voucher set status = :status where order_id = :orderID")
    void updateVoucherStatusForOrder(VoucherStatus status, Long orderID);

    @Query("SELECT * FROM voucher WHERE order_id = :orderID")
    List<Voucher> getVouchersForOrder(Long orderID);


    @Modifying
    @Query("update voucher set status = :status where id = :voucherID")
    void updateVoucherStatus(VoucherStatus status, Long voucherID);

    @Modifying
    @Query("update voucher set retry_count = retry_count+1, last_failure_reason=:lastFailureReason where id = :voucherID")
    void increaseRetryCount(Long voucherID, String lastFailureReason);

    Optional<VoucherEntity> findByVoucherCode(String voucherCode);
}
