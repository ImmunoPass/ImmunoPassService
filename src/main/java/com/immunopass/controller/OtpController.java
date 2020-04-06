package com.immunopass.controller;

import com.immunopass.controller.request.SendOtpRequest;
import com.immunopass.controller.request.VerifyOtpRequest;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/otps")
public interface OtpController {

    @PostMapping
    void sendOtp(@RequestBody SendOtpRequest sendOtpRequest);

    @PostMapping("/verify")
    @ResponseBody
    boolean verifyOtp(@RequestBody VerifyOtpRequest verifyOtpRequest);
}
