package com.immunopass.controller;

import com.immunopass.model.Voucher;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/v1/vouchers")
public interface VoucherController {

    @PostMapping("/claimVoucher")
    void claimVoucher(@Valid @RequestBody final String voucherCode);

    @GetMapping("/getVoucher")
    Voucher getVoucher(@Valid @RequestParam final String voucherCode);

}
