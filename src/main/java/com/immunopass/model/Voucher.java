package com.immunopass.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Voucher {
    private final Long id;
    private final String voucher_code;
    private final Long issuer_id;
    private final String user_name;
    private final String user_mobile;
    private final String user_emp_id;
    private final String user_government_id;
    private final String user_location;
    private final String status; // TODO:change from String to enum
    private final Long order_id;
    private final Long immunopass_id;
}
