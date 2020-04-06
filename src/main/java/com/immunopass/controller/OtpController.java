package com.immunopass.controller;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.immunopass.model.SendOtpRequest;
import com.immunopass.model.VerifyOtpRequest;
import com.immunopass.model.SendOtpResponse;
import com.immunopass.model.VerifyOtpResponse;


@RestController
@RequestMapping("/v1")
public interface OtpController {

    @PostMapping("/send_otp")
    @ResponseBody
    SendOtpResponse sendOtp(@Valid @RequestBody SendOtpRequest sendOtpRequest);

    @PostMapping("/verify_otp")
    @ResponseBody
    VerifyOtpResponse verifyOtp(@Valid @RequestBody VerifyOtpRequest verifyOtpRequest);
}
