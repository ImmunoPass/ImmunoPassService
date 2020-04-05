package com.immunopass.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetails {
    private Long accountId;
    private String phoneNumber;
    private String emailId;
}
