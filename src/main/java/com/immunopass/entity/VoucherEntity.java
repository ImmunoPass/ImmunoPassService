package com.immunopass.entity;

import java.time.LocalDateTime;
import javax.persistence.*;

import com.immunopass.enums.IDType;
import com.immunopass.enums.VoucherType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.immunopass.enums.VoucherStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(schema = "immunopass", name = "voucher")
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter(value = AccessLevel.PACKAGE)
public class VoucherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "voucher_code")
    private String voucherCode;

    @Column(name = "issuer_id")
    private Long issuerId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_mobile")
    private String userMobile;

    @Column(name = "user_emp_id")
    private String userEmpId;

    @Column(name = "user_government_id")
    private String userGovernmentId;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "user_govt_id_type")
    private IDType userGovtIDType;

    @Column(name = "user_location")
    private String userLocation;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private VoucherStatus status;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private VoucherType type;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "immunopass_id")
    private Long immunopassId;

    @Column(name = "retry_count")
    private Long retryCount;

    @Column(name = "last_failure_reason")
    private String lastFailureReason;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
