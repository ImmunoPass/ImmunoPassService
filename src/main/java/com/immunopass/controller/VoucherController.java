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
@RequestMapping("/v1")
public interface VoucherController {

    @PostMapping("/redeem_voucher")
    Voucher claimVoucher(@Valid @RequestBody final VoucherRequest voucherRequest);

    @PostMapping("/fetch_voucher")
    Voucher getVoucher(@Valid @RequestBody final VoucherRequest voucherRequest);

    //@GetMapping
    List<Voucher> getVouchers();

}
