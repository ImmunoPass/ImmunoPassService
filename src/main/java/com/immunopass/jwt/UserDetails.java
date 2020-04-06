package com.immunopass.jwt;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetails {
    private Long accountId;
    private String identifier;

}
