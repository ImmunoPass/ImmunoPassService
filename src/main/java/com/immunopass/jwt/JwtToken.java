package com.immunopass.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;


@Component
public class JwtToken {

    // token validity set to 5 hours
    private static final long JWT_TOKEN_VALIDITY = TimeUnit.DAYS.toMillis(1);

    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    private final String secret = "2FP8Wlc4ADlMdxti2jLRsdbasjkdbajskasl6781357263dsnmbdjsadkjuteqwiewF9IjBMQyYKsIVsBPIDgX";

    private final String issuer = "immunopass";

    private Key secretKey = Keys.hmacShaKeyFor(secret.getBytes());


    public Long getAccountIdFromToken(String token) throws NumberFormatException {
        return Long.parseLong(getClaimFromToken(token, Claims::getSubject));
    }

    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Boolean ignoreTokenExpiration(String token) {
        // here you specify tokens, for that the expiration is ignored
        return false;
    }

    public String generateToken(UserDetails userDetails) {
        // TODO serialize userDetails to claim map
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getAccountId().toString());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().
                setId(UUID.randomUUID().toString()).
                setClaims(claims).
                setSubject(subject).
                setIssuedAt(new Date(System.currentTimeMillis())).
                setIssuer(issuer).
                setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000)).
                signWith(secretKey, signatureAlgorithm).
                compact();
    }

    public Boolean canTokenBeRefreshed(String token) {
        return (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
