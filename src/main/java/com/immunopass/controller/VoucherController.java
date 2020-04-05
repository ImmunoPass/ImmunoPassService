package com.immunopass.controller;

import com.immunopass.model.Voucher;
import com.immunopass.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/vouchers")
public class VoucherController {
    private VoucherService voucherService;

    @Autowired
    public VoucherController(VoucherService voucherService) {
        this.voucherService = voucherService;
    }

    @GetMapping("/{id}")
    public Voucher getVoucher(@PathVariable Long id) {
        return voucherService.getVoucherById(id);
    }

    @GetMapping("/{id}/process")
    public void processVoucher(@PathVariable Long id, @RequestParam("action") String action) {
        voucherService.processVoucher(id, action);
    }
}
