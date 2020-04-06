package com.immunopass.jwt;

import com.immunopass.enums.AccountIdentifierType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {
    private Long accountId;
    private String identifier;
    private AccountIdentifierType identifierType;
}
