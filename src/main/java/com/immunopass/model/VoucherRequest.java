package com.immunopass.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@NotNull
@AllArgsConstructor
@NoArgsConstructor
public class VoucherRequest {
    @NotBlank
    private String voucherCode;
}
