package com.immunopass.service;

import com.immunopass.controller.OtpController;
import com.immunopass.entity.OtpEntity;
import com.immunopass.enums.IdentifierType;
import com.immunopass.enums.OtpStatus;
import com.immunopass.jwt.JwtToken;
import com.immunopass.model.SendOtpRequest;
import com.immunopass.model.SendOtpResponse;
import com.immunopass.model.VerifyOtpRequest;
import com.immunopass.model.VerifyOtpResponse;
import com.immunopass.repository.AccountRepository;
import com.immunopass.repository.OtpRepository;
import com.immunopass.restclient.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;


@Service
public class OtpService implements OtpController {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private OtpRepository otpRepository;
    @Autowired
    private JwtToken jwtToken;
    @Autowired
    private SMSService smsService;

    @Override
    public SendOtpResponse sendOtp(@RequestBody SendOtpRequest otpRequest) {
        return accountRepository
                .findByIdentifierAndIdentifierType(otpRequest.getIdentifier(), otpRequest.getIdentifierType())
                .map(accountEntity -> sendOtp(accountEntity.getIdentifier(), accountEntity.getIdentifierType(),
                        accountEntity.getName()))
                .orElse(null);
    }

    private SendOtpResponse sendOtp(String identifier, IdentifierType identifierType, String name) {
        return otpRepository
                .findFirstByIdentifierOrderByCreatedAtDesc(identifier)
                .filter(otpEntity -> LocalDateTime.now().isBefore(otpEntity.getValidTill()))
                .filter(otpEntity -> OtpStatus.UNVERIFIED.equals(otpEntity.getStatus()))
                .map(otpEntity -> {
                    if (otpEntity.getTryCount() < 3) {
                        otpEntity.setTryCount(otpEntity.getTryCount() + 1);
                        return otpToSendOtpResponse(otpRepository.save(otpEntity));
                    } else {
                        //TODO: Return Error Response
                        return SendOtpResponse.builder().build();
                    }
                })
                .orElseGet(() -> generateNewOtp(identifier, identifierType, name));
    }

    private SendOtpResponse generateNewOtp(String identifier, IdentifierType identifierType, String name) {
        OtpEntity otpEntity = OtpEntity.builder()
                .otp(smsService.generateNumSequence(6))
                .status(OtpStatus.UNVERIFIED)
                .tryCount(1)
                .validTill(LocalDateTime.now().plusMinutes(15))
                .identifier(identifier)
                .identifierType(identifierType)
                .build();
        otpEntity = otpRepository.save(otpEntity);
        smsService.sendOTPSMS(name, identifier, otpEntity.getOtp());
        return otpToSendOtpResponse(otpEntity);
    }

    private SendOtpResponse otpToSendOtpResponse(OtpEntity otpEntity) {
        return SendOtpResponse.builder()
                .identifier(otpEntity.getIdentifier())
                .identifierType(otpEntity.getIdentifierType())
                .status(otpEntity.getStatus())
                .tryCount(otpEntity.getTryCount())
                .validTill(otpEntity.getValidTill().toString())
                .build();
    }

    @Override
    public VerifyOtpResponse verifyOtp(VerifyOtpRequest otpRequest) {
        return otpRepository
                .findFirstByIdentifierAndOtpOrderByCreatedAtDesc(otpRequest.getIdentifier(), otpRequest.getOtp())
                .filter(otpEntity -> LocalDateTime.now().isBefore(otpEntity.getValidTill()))
                .filter(otpEntity -> OtpStatus.UNVERIFIED.equals(otpEntity.getStatus()))
                .map(otpEntity -> {
                    otpEntity.setStatus(OtpStatus.VERIFIED);
                    return otpRepository.save(otpEntity);
                })
                .map(this::verifyOtpSuccess)
                .orElseGet(this::verifyOtpFailure);
    }

    private VerifyOtpResponse verifyOtpSuccess(OtpEntity otpEntity) {
        return accountRepository
                .findByIdentifierAndIdentifierType(otpEntity.getIdentifier(), otpEntity.getIdentifierType())
                .map(accountEntity ->
                        VerifyOtpResponse
                                .builder()
                                .accessToken(jwtToken.generateToken(accountEntity))
                                .build())
                .orElse(null);
    }

    //TODO: Define proper failure response.
    private VerifyOtpResponse verifyOtpFailure() {
        return VerifyOtpResponse
                .builder()
                .accessToken("OTP verification failed.")
                .build();
    }

}
