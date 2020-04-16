package com.immunopass.util;

import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.immunopass.model.Account;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.jackson.io.JacksonDeserializer;
import io.jsonwebtoken.lang.Maps;
import io.jsonwebtoken.security.Keys;


@Component
public class JwtUtil {

    private static final String ACCOUNT = "account";
    private static final int EXPIRY_DURATION = 4 * 60 * 60 * 1000;

    private final SecretKey secretKey;

    public JwtUtil(@Value("${jwt.secret_key}") String secretKey) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public String generateToken(Account account) {
        return Jwts.builder()
                .claim(ACCOUNT, account)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRY_DURATION))
                .signWith(secretKey)
                .compact();
    }

    public Account extractAccount(String token) {
        return Jwts.parserBuilder()
                .deserializeJsonWith(new JacksonDeserializer(Maps.of(ACCOUNT, Account.class).build()))
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get(ACCOUNT, Account.class);
    }

}
