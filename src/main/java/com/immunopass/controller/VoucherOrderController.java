package com.immunopass.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/voucher_orders")
public interface VoucherOrderController {

    @PostMapping("")
    public void createVoucherOrder(@RequestParam("file") MultipartFile file);

}
