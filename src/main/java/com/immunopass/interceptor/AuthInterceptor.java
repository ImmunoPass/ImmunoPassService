package com.immunopass.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import com.immunopass.constants.RequestHeader;
import com.immunopass.service.AuthService;


public class AuthInterceptor implements HandlerInterceptor {

    @Autowired private AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String accessToken = request.getHeader(RequestHeader.ACCESS_TOKEN);

        if (accessToken == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return false;
        } else if (authService.isAuthenticated(accessToken) != null) {
            return true;
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
    }

}
