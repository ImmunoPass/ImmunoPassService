package com.immunopass.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.immunopass.service.OtpService;


@RestController
@RequestMapping("/v1/otps")
public class OtpController {
    // TODO: replace dummy impls
    private final OtpService otpService;

    @Autowired
    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }

    @PostMapping
    public boolean createOtp(@RequestParam("action") String action,
            @RequestParam("identifier") String identifier,
            @RequestParam("identifier_type") String identifier_type,
            @RequestParam(value = "Otp", required = false) String otp) {
        if (action == "send") {
            return otpService.sendSMS(identifier, identifier_type);
        } else if (action == "resend") {
            return otpService.resendSMS(identifier, identifier_type);
        } else if (action == "verify") {
            assert (otp != null);
            return otpService.verifyOtp(otp, identifier, identifier_type);
        } else {
            return false;
        }
    }
}
