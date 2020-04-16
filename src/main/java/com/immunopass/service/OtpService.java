package com.immunopass.service;

import java.time.LocalDateTime;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import com.immunopass.controller.OtpController;
import com.immunopass.entity.OtpEntity;
import com.immunopass.enums.AccountType;
import com.immunopass.enums.IdentifierType;
import com.immunopass.enums.OtpStatus;
import com.immunopass.mapper.AccountMapper;
import com.immunopass.model.SendOtpRequest;
import com.immunopass.model.SendOtpResponse;
import com.immunopass.model.VerifyOtpRequest;
import com.immunopass.model.VerifyOtpResponse;
import com.immunopass.repository.AccountRepository;
import com.immunopass.repository.OtpRepository;
import com.immunopass.restclient.SMSService;
import com.immunopass.util.JwtUtil;


@Service
public class OtpService implements OtpController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private OtpRepository otpRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private SMSService smsService;

    @Override
    public SendOtpResponse sendOtp(@RequestBody SendOtpRequest otpRequest) {
        return accountRepository
                .findByIdentifierAndIdentifierType(otpRequest.getIdentifier(), otpRequest.getIdentifierType())
                .filter(accountEntity -> {
                    if (otpRequest.getAccountType() == AccountType.ORGANIZATION) {
                        if (accountEntity.getOrganizationId() != null) {
                            return true;
                        } else {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                    "User account isn't linked to any organization.");
                        }
                    } else if (otpRequest.getAccountType() == AccountType.PATHOLOGY_LAB) {
                        if (accountEntity.getPathologyLabId() != null) {
                            return true;
                        } else {
                            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                    "User account isn't linked to any pathology lab.");
                        }
                    } else {
                        return false;
                    }
                })
                .map(accountEntity ->
                        sendOtp(accountEntity.getIdentifier(),
                                accountEntity.getIdentifierType(),
                                accountEntity.getName()))
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST, "User account doesn't exist."));
    }

    private SendOtpResponse sendOtp(String identifier, IdentifierType identifierType, String name) {
        return otpRepository
                .findFirstByIdentifierOrderByCreatedAtDesc(identifier)
                .filter(otpEntity -> !OtpStatus.VERIFIED.equals(otpEntity.getStatus()))
                .filter(otpEntity -> LocalDateTime.now().isBefore(otpEntity.getValidTill()))
                .map(otpEntity -> {
                    if (otpEntity.getRetryCount() < 2) {
                        otpEntity.setRetryCount(otpEntity.getRetryCount() + 1);
                        return sendOtp(name, otpRepository.save(otpEntity));
                    } else {
                        throw new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                "Retry attempts over. Please try after 15 minutes.");
                    }
                })
                .orElseGet(() -> {
                    OtpEntity otpEntity = OtpEntity.builder()
                            .otp(RandomStringUtils.randomNumeric(6))
                            .status(OtpStatus.UNVERIFIED)
                            .retryCount(0)
                            .verificationAttempts(0)
                            .validTill(LocalDateTime.now().plusMinutes(15))
                            .identifier(identifier)
                            .identifierType(identifierType)
                            .build();
                    return sendOtp(name, otpRepository.save(otpEntity));
                });
    }

    private SendOtpResponse sendOtp(String name, OtpEntity otpEntity) {
        boolean otpSendSuccess = smsService.sendOTPSMS(name, otpEntity.getIdentifier(), otpEntity.getOtp());
        if (otpSendSuccess) {
            return SendOtpResponse.builder()
                    .identifier(otpEntity.getIdentifier())
                    .identifierType(otpEntity.getIdentifierType())
                    .status(otpEntity.getStatus())
                    .retryCount(otpEntity.getRetryCount())
                    .validTill(otpEntity.getValidTill().toString())
                    .build();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Something went wrong while sending OTP.");
        }
    }

    @Override
    public VerifyOtpResponse verifyOtp(VerifyOtpRequest otpRequest) {
        return otpRepository
                .findFirstByIdentifierOrderByCreatedAtDesc(otpRequest.getIdentifier())
                .filter(otpEntity -> LocalDateTime.now().isBefore(otpEntity.getValidTill()))
                .filter(otpEntity -> OtpStatus.UNVERIFIED.equals(otpEntity.getStatus()))
                .map(otpEntity -> {
                    if (otpEntity.getOtp().equals(otpRequest.getOtp())) {
                        otpEntity.setVerificationAttempts(otpEntity.getVerificationAttempts() + 1);
                        otpEntity.setStatus(OtpStatus.VERIFIED);
                        return otpRepository.save(otpEntity);
                    } else if (otpEntity.getVerificationAttempts() < 3) {
                        otpEntity.setVerificationAttempts(otpEntity.getVerificationAttempts() + 1);
                        otpRepository.save(otpEntity);
                        return null;
                    } else {
                        otpEntity.setVerificationAttempts(otpEntity.getVerificationAttempts() + 1);
                        otpEntity.setStatus(OtpStatus.INVALID);
                        otpRepository.save(otpEntity);
                        return null;
                    }
                })
                .map(this::generateAuthTokenAndReturnSuccessResponse)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.BAD_REQUEST, "OTP is either invalid or has expired."));
    }

    private VerifyOtpResponse generateAuthTokenAndReturnSuccessResponse(OtpEntity otpEntity) {
        return accountRepository
                .findByIdentifierAndIdentifierType(otpEntity.getIdentifier(), otpEntity.getIdentifierType())
                .map(AccountMapper::map)
                .map(account ->
                        VerifyOtpResponse
                                .builder()
                                .accessToken(jwtUtil.generateToken(account))
                                .build())
                .orElse(null);
    }

}
