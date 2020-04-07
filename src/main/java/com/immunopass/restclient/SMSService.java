package com.immunopass.restclient;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class SMSService {
    private final int OTP_LENGTH = 6;
    private final String numbers = "0123456789";
    private Random random;
    private RestTemplate restTemplate;
    @Value("${sms.endpoint}")
    private String endpoint;
    @Value("${sms.auth}")
    private String auth;

    public SMSService() {
        random = new Random();
        restTemplate = new RestTemplate();

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        //Add the Jackson Message converter
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        // Note: here we are making this converter to process any kind of response,
        // not only application/*json, which is the default behaviour
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
    }

    public String generateOtp() {
        char[] otp = new char[OTP_LENGTH];
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp[i] =
                    numbers.charAt(random.nextInt(numbers.length()));
        }
        return new String(otp);
    }

    public boolean sendOTPSMS(String userName, String to, String otp) {
        LoginOtpRequest otpRequest = LoginOtpRequest.builder()
                .otp(otp)
                .to(to)
                .userName(userName).build();
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Authentication", auth);
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        List<MediaType> accepts = new ArrayList<>();
        accepts.add(MediaType.ALL);
        requestHeaders.setAccept(accepts);
        try {
            RequestEntity<LoginOtpRequest> requestEntity =
                    new RequestEntity<LoginOtpRequest>(
                            otpRequest,
                            requestHeaders,
                            HttpMethod.POST,
                            new URI(endpoint + "/v1/sms/login-otp")
                    );
            ResponseEntity<LoginOtpResponse> otpResponse = restTemplate.exchange(requestEntity, LoginOtpResponse.class);

            if (otpResponse.getStatusCode() == HttpStatus.OK) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;

    }

}
