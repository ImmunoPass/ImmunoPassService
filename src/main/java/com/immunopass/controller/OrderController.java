package com.immunopass.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

import static com.immunopass.constants.RequestHeader.ACCESS_TOKEN;

@RestController
@RequestMapping("/v1/orders")
public interface OrderController {

    @PostMapping("/createOrder")
    public void createOrder(@RequestParam("file") MultipartFile file,
                            @RequestHeader(ACCESS_TOKEN) String accessToken);

    @PostMapping("/createVouchers")
    public void createVouchers(@RequestParam("id") Long id,
                               @RequestHeader(ACCESS_TOKEN) String accessToken);

    @PostMapping("/createVouchers")
    public void processOrders(@RequestParam("id") Long id,
                              @RequestHeader(ACCESS_TOKEN) String accessToken);
}
