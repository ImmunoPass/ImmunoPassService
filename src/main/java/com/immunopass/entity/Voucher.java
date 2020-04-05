package com.immunopass.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.immunopass.enums.VoucherStatus;


@Entity(name = "voucher")
public class Voucher {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String voucherCode;
    private Long issuerId;
    private String userName;
    private String userMobile;
    private String userEmpId;
    private String userGovernmentId;
    private String userLocation;
    private VoucherStatus status;
    private Long orderId;
    private Long immunopassId;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
