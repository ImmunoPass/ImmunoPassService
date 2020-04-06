package com.immunopass.restclient;

import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SMSCallerAPI {

    public static void main(String[] args) throws Exception {
        SMSCallerAPI api = new SMSCallerAPI();
        RestTemplate restTemplate = new RestTemplate();

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        //Add the Jackson Message converter
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        // Note: here we are making this converter to process any kind of response,
        // not only application/*json, which is the default behaviour
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
        api.run(restTemplate);
    }

    public void run(RestTemplate restTemplate) throws Exception {
            LoginOtpRequest otpRequest = LoginOtpRequest.builder()
                    .otp("201917")
                    .to("9757147940")
                    .userName("John Doe").build();
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.set("Authentication", "4db2c2e9de64630879effbde15720a16");
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            List<MediaType> accepts = new ArrayList<>();
            accepts.add(MediaType.ALL);
            requestHeaders.setAccept(accepts);
            RequestEntity<LoginOtpRequest> requestEntity =
                    new RequestEntity<LoginOtpRequest>(
                            otpRequest,
                            requestHeaders,
                            HttpMethod.POST,
                            new URI("http://13.235.128.146:3000/v1/sms/login-otp")
                    );
            ResponseEntity<LoginOtpResponse> otpResponse = restTemplate.exchange(
                    requestEntity, LoginOtpResponse.class);

            System.out.println(otpResponse.getBody().getStatus());
    }
}
