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

}
