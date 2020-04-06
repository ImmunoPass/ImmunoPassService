package com.immunopass.service;

import com.immunopass.controller.OtpController;
import com.immunopass.controller.request.SendOtpRequest;
import com.immunopass.controller.request.VerifyOtpRequest;
import com.immunopass.controller.response.VerifyOtpResponse;
import com.immunopass.jwt.JwtToken;
import com.immunopass.jwt.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


@Service
public class OtpService implements OtpController {

    @Autowired private JwtToken jwtToken;

    @Override
    public void sendOtp(@RequestBody SendOtpRequest sendOtpRequest) {
        System.out.println("Sending otp to " + sendOtpRequest.getIdentifier());
    }

    @Override
    public VerifyOtpResponse verifyOtp(@RequestBody VerifyOtpRequest verifyOtpRequest) {
        // TODO verify otp

        // TODO get account id from identifier

        UserDetails userDetails = new UserDetails();
        userDetails.setAccountId(1234l);
        userDetails.setIdentifier(verifyOtpRequest.getIdentifier());
        userDetails.setIdentifierType(verifyOtpRequest.getIdentifier_type());
        String accessToken = jwtToken.generateToken(userDetails);
        return new VerifyOtpResponse(accessToken);
    }
}
