package com.immunopass.controller;

import javax.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.immunopass.model.Voucher;


@RestController
@RequestMapping("/v1/vouchers")
public interface VoucherController {

    @PostMapping("")
    Voucher createVoucher(@NotNull @RequestBody final Voucher voucher);

    @GetMapping("/{id}")
    Voucher getVoucher(@PathVariable Long id);

    @GetMapping("/{id}/process")
    void processVoucher(@PathVariable Long id, @RequestParam("action") String action);
}
