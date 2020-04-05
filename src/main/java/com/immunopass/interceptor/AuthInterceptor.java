package com.immunopass.interceptor;

import com.immunopass.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Objects;

import static com.immunopass.constants.RequestHeader.ACCESS_TOKEN;

public class AuthInterceptor implements HandlerInterceptor {

    @Autowired private AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String accessToken = request.getHeader(ACCESS_TOKEN);
        if (Objects.isNull(accessToken)) {
            return false;
        }

        if (authService.isAuthenticated(accessToken)) {
            return true;
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }

}
