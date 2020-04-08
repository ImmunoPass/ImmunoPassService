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
import com.immunopass.repository.AccountRepository;
import com.immunopass.utils.JwtUtil;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired private JwtUtil jwtUtil;

    @Autowired private AccountRepository accountRepository;

    @Override
    protected void doFilterInternal(final HttpServletRequest httpServletRequest,
            final HttpServletResponse httpServletResponse, final FilterChain filterChain)
            throws ServletException, IOException {
        String authorizationHeader = httpServletRequest.getHeader("Authorization");

        Long accountId = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {
                accountId = Long.parseLong(jwtUtil.extractAccountId(jwt));
            } catch (NumberFormatException e) {
                // TODO: Log error
            }
        }

        if (accountId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            final String finalJwt = jwt;
            accountRepository.findById(accountId)
                    .ifPresent(accountEntity -> {
                        if (jwtUtil.validateToken(finalJwt, accountEntity)) {
                            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                                    new UsernamePasswordAuthenticationToken(accountEntity, null, null);
                            usernamePasswordAuthenticationToken
                                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                        }
                    });
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
