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

    private String voucherCode;

    private Long issuerId;

    private String userName;

    private String userMobile;

    private String userEmpId;

    private String userGovernmentId;

    @Enumerated(value = EnumType.STRING)
    private IDType userGovtIdType;

    private String userLocation;

    @Enumerated(value = EnumType.STRING)
    private VoucherStatus status;

    private Long orderId;

    private Long immunopassId;

    private Long retryCount;

    private String lastFailureReason;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
