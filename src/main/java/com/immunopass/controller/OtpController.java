package com.immunopass.controller;

import com.immunopass.controller.request.SendOtpRequest;
import com.immunopass.controller.request.VerifyOtpRequest;
import com.immunopass.controller.response.SendOtpResponse;
import com.immunopass.controller.response.VerifyOtpResponse;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/otps")
public interface OtpController {

    @PostMapping
    @ResponseBody
    SendOtpResponse sendOtp(@RequestBody SendOtpRequest sendOtpRequest);

    @PostMapping("/verify")
    @ResponseBody
    VerifyOtpResponse verifyOtp(@RequestBody VerifyOtpRequest verifyOtpRequest);
}
