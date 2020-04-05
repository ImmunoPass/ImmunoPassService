package com.immunopass.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import com.immunopass.controller.OtpController;


@Service
public class OtpService implements OtpController {

    @Override
    public boolean createOtp(@RequestParam("action") String action,
            @RequestParam("identifier") String identifier,
            @RequestParam("identifier_type") String identifier_type,
            @RequestParam(value = "Otp", required = false) String otp) {
        if (action == "send") {
            return sendSMS(identifier, identifier_type);
        } else if (action == "resend") {
            return resendSMS(identifier, identifier_type);
        } else if (action == "verify") {
            assert (otp != null);
            return verifyOtp(otp, identifier, identifier_type);
        } else {
            return false;
        }
    }

    public boolean sendSMS(String identifier, String identifier_type) {
        System.out.println("Sending SMS to " + identifier);
        return true;
    }

    public boolean resendSMS(String identifier, String identifier_type) {
        System.out.println("Resending SMS to " + identifier);
        return true;
    }

    public boolean verifyOtp(String otp, String identifier, String identifier_type) {

        // TODO make jwt generate call
        //JwtTokenUtil.generateToken()

        return otp == "123";
    }
}
