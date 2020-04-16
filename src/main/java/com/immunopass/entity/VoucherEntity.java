package com.immunopass.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.immunopass.enums.IDType;
import com.immunopass.enums.VoucherStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(schema = "immunopass", name = "voucher")
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Data
public class VoucherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String voucherCode;

    private Long issuerAccountId;

    private Long issuerOrganizationId;

    private String userName;

    private String userMobile;

    private String userEmpId;

    private String userGovernmentId;

    @Enumerated(value = EnumType.STRING)
    private IDType userGovtIdType;

    private String userLocation;

    @Enumerated(value = EnumType.STRING)
    private VoucherStatus status;

    private Long redeemedAccountId;

    private Long redeemedPathologyLabId;

    private Long orderId;

    private Long immunopassId;

    private Long retryCount;

    private String lastFailureReason;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
