package com.immunopass.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.immunopass.model.Account;
import com.immunopass.repository.AccountRepository;
import com.immunopass.util.JwtUtil;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired private JwtUtil jwtUtil;

    @Autowired private AccountRepository accountRepository;

    @Override
    protected void doFilterInternal(final HttpServletRequest httpServletRequest,
            final HttpServletResponse httpServletResponse, final FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        Account account = null;
        boolean isTokenExpired = true;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwt = authorizationHeader.substring(7);
            account = jwtUtil.extractAccount(jwt);
            isTokenExpired = jwtUtil.isTokenExpired(jwt);
        }

        if (account != null && !isTokenExpired && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(account, null, null);
            usernamePasswordAuthenticationToken
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
