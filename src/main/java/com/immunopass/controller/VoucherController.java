package com.immunopass.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.immunopass.model.Voucher;
import com.immunopass.service.VoucherService;


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
