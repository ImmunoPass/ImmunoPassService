package com.immunopass.jwt;

import com.immunopass.enums.AccountIdentifierType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetails {
    private Long accountId;
    private String identifier;

}
