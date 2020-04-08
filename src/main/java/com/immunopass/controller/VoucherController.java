package com.immunopass.controller;

import com.immunopass.model.Voucher;
import com.immunopass.model.VoucherRequest;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/v1/vouchers")
public interface VoucherController {

    @PostMapping("/claim_voucher")
    void claimVoucher(@Valid @RequestBody final VoucherRequest voucherRequest);

    @PostMapping("/voucher_code")
    Voucher getVoucher(@Valid @RequestBody final VoucherRequest voucherRequest);

}
