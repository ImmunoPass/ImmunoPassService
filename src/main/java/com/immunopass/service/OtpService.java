package com.immunopass.service;

import org.springframework.stereotype.Service;

@Service
public class OtpService {

    public boolean sendSMS(String identifier, String identifier_type) {
        System.out.println("Sending SMS to " + identifier);
        return true;
    }

    public boolean resendSMS(String identifier, String identifier_type) {
        System.out.println("Resending SMS to " + identifier);
        return true;
    }

    public boolean verifyOtp(String otp, String identifier, String identifier_type) {
        if (otp == "123") return true;
        return false;
    }
}
