package com.immunopass.service;

import com.immunopass.jwt.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {
    public boolean isAuthenticated(String accessToken) {
        return JwtTokenUtil.validateToken(accessToken);
    }
}
