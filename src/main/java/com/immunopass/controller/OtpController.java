package com.immunopass.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/otps")
public interface OtpController {

    @PostMapping("")
    boolean createOtp(@RequestParam("action") String action,
            @RequestParam("identifier") String identifier,
            @RequestParam("identifier_type") String identifier_type,
            @RequestParam(value = "Otp", required = false) String otp);
}
