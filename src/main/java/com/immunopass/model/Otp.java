package com.immunopass.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Otp {
    private final Long id;
    private final Long valid_till;
    private final String otp;
    private final Integer try_count;
    private final String status; // TODO: Change from string to enum
}
