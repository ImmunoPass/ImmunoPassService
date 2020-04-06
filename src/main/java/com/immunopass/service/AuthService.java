package com.immunopass.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthService {
    public boolean isAuthenticated(String accessToken) {
        return true;
        // JwtTokenUtil.validateToken(accessToken);
    }
}
