package com.immunopass.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.immunopass.model.Account;
import com.immunopass.util.JwtUtil;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(final HttpServletRequest httpServletRequest,
            final HttpServletResponse httpServletResponse, final FilterChain filterChain)
            throws ServletException, IOException {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            Account account = jwtUtil.extractAccount(authorizationHeader.substring(7));
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(account, null, null);
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
