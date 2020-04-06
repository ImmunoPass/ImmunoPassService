package com.immunopass.service;

import com.immunopass.jwt.JwtToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {


    @Autowired
    private JwtToken jwtToken;

    public Long isAuthenticated(String accessToken) {
        return jwtToken.validateToken(accessToken);
    }
}
