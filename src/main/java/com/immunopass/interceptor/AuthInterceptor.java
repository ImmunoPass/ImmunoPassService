package com.immunopass.interceptor;

import com.immunopass.constants.RequestHeader;
import com.immunopass.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AuthInterceptor implements HandlerInterceptor {

    @Autowired private AuthService authService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String accessToken = request.getHeader(RequestHeader.ACCESS_TOKEN);

        if (accessToken == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "AuthToken missing.");
            return false;
        } else if (authService.isAuthenticated(accessToken) != null) {
            request.setAttribute("accountId", authService.isAuthenticated(accessToken));
            return true;
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized request!");
            return false;
        }
    }

}
