package com.immunopass.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Order {
    private final Long id;
    private final Integer voucher_count;
    private final String uploaded_file;
    private final String status; // TODO: change from string to enum
    private final Long created_by;
}
