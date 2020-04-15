package com.immunopass.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.immunopass.model.Voucher;
import com.immunopass.model.VoucherRequest;


@RestController
@RequestMapping("/v1/vouchers")
public interface VoucherController {

    @PostMapping("/claim_voucher")
    void claimVoucher(@Valid @RequestBody final VoucherRequest voucherRequest);

    @PostMapping("/voucher_code")
    Voucher getVoucher(@Valid @RequestBody final VoucherRequest voucherRequest);

    //@GetMapping
    List<Voucher> getVouchers();

}
