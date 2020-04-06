package com.immunopass.entity;

import com.immunopass.enums.IdentifierType;
import com.immunopass.enums.OtpStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(schema = "immunopass", name = "otp")
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
public class OtpEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime validTill;

    private String otp;

    private Integer tryCount;

    private String identifier;

    @Enumerated(value= EnumType.STRING)
    private IdentifierType identifierType;

    @Enumerated(value = EnumType.STRING)
    private OtpStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
