package com.immunopass.restclient;

import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class SMSService {
    private final int OTP_LENGTH = 6;
    private final String numbers = "0123456789";
    private Random random;
    private RestTemplate restTemplate;
    private final String endpoint = "http://13.235.128.146:3000";

    public SMSService() {
        random = new Random();
        RestTemplate restTemplate = new RestTemplate();

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
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
        requestHeaders.set("Authentication", "4db2c2e9de64630879effbde15720a16");
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
                            new URI( endpoint+"/v1/sms/login-otp")
                    );
            ResponseEntity<LoginOtpResponse> otpResponse = restTemplate.exchange(
                    requestEntity, LoginOtpResponse.class);

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
