package com.immunopass.controller;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.immunopass.controller.request.SendOtpRequest;
import com.immunopass.controller.request.VerifyOtpRequest;
import com.immunopass.controller.response.SendOtpResponse;
import com.immunopass.controller.response.VerifyOtpResponse;


@RestController
@RequestMapping("/v1/otps")
public interface OtpController {

    @PostMapping
    @ResponseBody
    SendOtpResponse sendOtp(@Valid @RequestBody SendOtpRequest sendOtpRequest);

    @PostMapping("/verify")
    @ResponseBody
    VerifyOtpResponse verifyOtp(@Valid @RequestBody VerifyOtpRequest verifyOtpRequest);
}
