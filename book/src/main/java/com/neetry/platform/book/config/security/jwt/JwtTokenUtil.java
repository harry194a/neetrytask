package com.neetry.platform.book.config.security.jwt;


import com.neetry.platform.iam.domain.oauth.jwt.JwtClaim;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Harutyun Badeyan
 * Date: 26.03.24
 * Time: 12:54
 */
@Component
public class JwtTokenUtil {
    
    private final String secret;

    public JwtTokenUtil(
            @Value("${neetry.platform.iam.jwt-secret}") final String secret
    ) {
        this.secret = secret;
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, JwtClaim.SUB, String.class);
    }

    public Instant getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, JwtClaim.EXPIRATION, Date.class).toInstant();
    }

    public <T> T getClaimFromToken(String token, String claim, Class<T> claz) {
        final Claims claims = getAllClaimsFromToken(token);
        if (claims.containsKey(claim)) {
            return claims.get(claim, claz);
        }
        return null;
    }

    public Claims getAllClaimsFromToken(String token) {
        final SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        Jws<Claims> jwsClaims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);
        return jwsClaims.getBody();
    }

    public Boolean isTokenExpired(String token) {
        final Instant expiration = getExpirationDateFromToken(token);
        return expiration.isBefore(Instant.now());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        checkJwtClaims(token);
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public void validateToken(String token) {
        checkJwtClaims(token);
        isTokenExpired(token);
    }

    public Collection<GrantedAuthority> extractGrantedAuthorities(String token) {
        List<String> authorities = getClaimFromToken(token, JwtClaim.AUTHORITIES, List.class);
        if (authorities == null) {
            return Collections.emptyList();
        }
        return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    private void checkJwtClaims(String token) {
        List<String> requiredClaims = Arrays.asList(
                JwtClaim.ID,
                JwtClaim.SUB,
                JwtClaim.EMAIL,
                JwtClaim.EXPIRATION,
                JwtClaim.FIRST_NAME,
                JwtClaim.LAST_NAME,
                JwtClaim.AUTHORITIES
        );
        Claims claims = getAllClaimsFromToken(token);
        requiredClaims.forEach(claim -> {
            if (!claims.containsKey(claim)) {
                throw new IllegalArgumentException("JWT token doesn't contain all required claims");
            }
        });
    }
}