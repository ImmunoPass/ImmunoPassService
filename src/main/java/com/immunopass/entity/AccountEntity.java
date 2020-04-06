package com.immunopass.entity;

import com.immunopass.enums.EntityStatus;
import com.immunopass.enums.IdentifierType;
import com.immunopass.enums.OrganizationType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(schema = "immunopass", name = "account")
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter(value = AccessLevel.PACKAGE)
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String identifier;

    @Enumerated(value = EnumType.STRING)
    private IdentifierType identifierType;

    private String passwordHash;

    private Long organizationId;

    @Enumerated(value = EnumType.STRING)
    private OrganizationType organizationType;

    @Enumerated(value = EnumType.STRING)
    private EntityStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
