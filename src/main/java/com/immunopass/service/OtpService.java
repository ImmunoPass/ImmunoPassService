package com.immunopass.service;

import com.immunopass.controller.OtpController;
import com.immunopass.controller.request.SendOtpRequest;
import com.immunopass.controller.request.VerifyOtpRequest;
import com.immunopass.controller.response.SendOtpResponse;
import com.immunopass.controller.response.VerifyOtpResponse;
import com.immunopass.entity.OtpEntity;
import com.immunopass.enums.IdentifierType;
import com.immunopass.enums.OtpStatus;
import com.immunopass.jwt.JwtToken;
import com.immunopass.jwt.UserDetails;
import com.immunopass.model.Otp;
import com.immunopass.repository.AccountRepository;
import com.immunopass.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;


@Service
public class OtpService implements OtpController {
    private static Long TEN_MINUTES_MILLIS = new Long(600000);

    @Autowired
    OtpRepository otpRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    private JwtToken jwtToken;


    @Override
    public SendOtpResponse sendOtp(@RequestBody SendOtpRequest sendOtpRequest) {
        // TODO: Implement logic to send SMS
        Otp otp = fetchOtpByIdentifierAndIdentifierType(sendOtpRequest.getIdentifier(), sendOtpRequest.getIdentifierType());
        if (otp == null) {
            OtpEntity otpEntity = OtpEntity.builder()
                    .otp(generateNewOtp())
                    .status(OtpStatus.UNVERIFIED)
                    .tryCount(1)
                    .validTill(LocalDateTime.ofInstant(
                            Instant.ofEpochMilli(System.currentTimeMillis() + TEN_MINUTES_MILLIS),
                            TimeZone.getDefault().toZoneId()))
                    .identifier(sendOtpRequest.getIdentifier())
                    .identifierType(sendOtpRequest.getIdentifierType())
                    .build();

            otp =  mapEntityToModel(otpRepository.save(otpEntity));
        } else {
            OtpEntity otpEntity = otpRepository.findById(otp.getId()).get();
            if (otp.getTryCount() <=1) {
                otpEntity.setTryCount(otp.getTryCount() + 1);
            } else {
                otpEntity.setTryCount(3);
                otpEntity.setStatus(OtpStatus.INVALID);
            }
            otp = mapEntityToModel(otpRepository.save(otpEntity));
        }
        return otpToSendOtpResponse(otp);
    }

    private SendOtpResponse otpToSendOtpResponse(Otp otp) {
        return SendOtpResponse.builder()
                .identifier(otp.getIdentifier())
                .identifier_type(otp.getIdentifier_type())
                .status(otp.getStatus())
                .tryCount(otp.getTryCount())
                .validTill(otp.getValidTill().toString())
                .build();
    }

    private String generateNewOtp() {
        return "123456";
    }



    @Override
    public VerifyOtpResponse verifyOtp(VerifyOtpRequest verifyOtpRequest) {
        Otp otp = fetchOtpByIdentifier(verifyOtpRequest.getIdentifier());
        if (otp == null) {
            return VerifyOtpResponse.builder()
                    .accessToken("Otp failed")
                    .build();
        } else if (!otp.getOtp().equals(verifyOtpRequest.getOtp())) {
            return VerifyOtpResponse.builder()
                    .accessToken("Otp failed")
                    .build();
        }
        UserDetails userDetails = UserDetails.builder()
                .accountId(1234l)
                .identifier(verifyOtpRequest.getIdentifier())
                .build();
        String accessToken = jwtToken.generateToken(userDetails);

        return VerifyOtpResponse.builder()
                .accessToken(accessToken).build();
    }

    public Otp fetchOtpByIdentifier(String identifier) {
        return otpRepository
                .findByIdentifier(identifier)
                .map(this::mapEntityToModel)
                .orElse(null);
    }

    public Otp fetchOtpByIdentifierAndIdentifierType(String identifier, IdentifierType identifierType) {
        return otpRepository
                .findByIdentifierAndIdentifierType(identifier, identifierType)
                .map(this::mapEntityToModel)
                .orElse(null);
    }

    private Otp mapEntityToModel(OtpEntity otpEntity) {
        return Otp.builder().id(otpEntity.getId())
                .otp(otpEntity.getOtp())
                .status(otpEntity.getStatus())
                .tryCount(otpEntity.getTryCount())
                .validTill(otpEntity.getValidTill())
                .identifier(otpEntity.getIdentifier())
                .identifier_type(otpEntity.getIdentifierType())
                .build();

    }
}
