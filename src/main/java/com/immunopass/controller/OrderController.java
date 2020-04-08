package com.immunopass.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/orders")
public interface OrderController {

    @PostMapping("/createOrder")
    public void createOrder(@RequestParam("file") MultipartFile file);

    //todo: Remove after writing cron for creating vouchers.
    @PostMapping("/createVouchers")
    public void createVouchers(@RequestParam("id") Long id);

    //todo: Remove after writing cron for processing orders.
    @PostMapping("/processOrder")
    public void processOrder(@RequestParam("id") Long id);
}
