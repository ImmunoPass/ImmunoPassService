package com.immunopass.controller;

import com.immunopass.model.Voucher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/v1/vouchers")
public interface VoucherController {

    @PostMapping("/claimVoucher")
    void claimVoucher(@Valid @RequestBody final String voucherCode);

    @PostMapping("/claimVoucher")
    Voucher getVoucher(@Valid @RequestBody final String voucherCode);

}
