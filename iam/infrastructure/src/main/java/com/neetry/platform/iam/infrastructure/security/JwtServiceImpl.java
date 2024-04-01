package com.neetry.platform.iam.infrastructure.security;

import com.neetry.platform.iam.domain.oauth.jwt.CreateJwtCommand;
import com.neetry.platform.iam.domain.oauth.jwt.JwtClaim;
import com.neetry.platform.iam.domain.oauth.jwt.JwtService;
import com.neetry.platform.iam.domain.oauth.jwt.JwtUserInfo;
import com.neetry.platform.iam.domain.oauth.jwt.exception.InvalidJwtTokenException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class JwtServiceImpl implements JwtService {

    public static final SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_TYPE_HEADER = "typ";
    public static final List<String> REQUIRED_CLAIMS = List.of(JwtClaim.ID, JwtClaim.SUB, JwtClaim.EMAIL);

    private final String jwtSecret;

    @Autowired
    public JwtServiceImpl(
            @Value("${neetry.platform.iam.jwt-secret}") String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    @Override
    public String createJwt(CreateJwtCommand command) {
        final var jti = UUID.randomUUID().toString();
        final var issuedAt = Date.from(command.getIssuedAt());
        final var expireAt = Date.from(command.getExpiresAt());
        final Key signingKey = new SecretKeySpec(jwtSecret.getBytes(), algorithm.getJcaName());
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaim.ID, command.getJwtUserInfo().getUserId().toString());
        claims.put(JwtClaim.SUB, command.getJwtUserInfo().getEmail());
        claims.put(JwtClaim.EMAIL, command.getJwtUserInfo().getEmail());
        claims.put(JwtClaim.FIRST_NAME, command.getJwtUserInfo().getFirstName());
        claims.put(JwtClaim.LAST_NAME, command.getJwtUserInfo().getLastName());
        claims.put(JwtClaim.AUTHORITIES, List.of(command.getJwtUserInfo().getRole()));

        return Jwts.builder()
                .setId(jti)
                .setIssuedAt(issuedAt)
                .setExpiration(expireAt)
                .setHeaderParam(TOKEN_TYPE_HEADER, TOKEN_TYPE)
                .addClaims(claims)
                .signWith(signingKey, algorithm)
                .compact();
    }

    @Override
    public JwtUserInfo parseJwt(String jwtToken) {
        try {
            var jwt = getJwtDecoder().decode(jwtToken);
            validate(jwt);
            return new JwtUserInfo(jwt.getClaim(JwtClaim.ID),
                    jwt.getClaimAsString(JwtClaim.EMAIL),
                    jwt.getClaimAsString(JwtClaim.FIRST_NAME),
                    jwt.getClaimAsString(JwtClaim.LAST_NAME),
                    jwt.getClaimAsString(JwtClaim.AUTHORITIES)
            );
        } catch (JwtException e) {
            throw new InvalidJwtTokenException(String.format("Invalid jwt Token - %s", jwtToken));
        }
    }

    private void validate(Jwt jwt) {
        if (!jwt.getClaims().keySet().containsAll(REQUIRED_CLAIMS)) {
            throw new InvalidJwtTokenException("Missing required claims");
        }
    }

    public JwtDecoder getJwtDecoder() {
        SecretKey key = new SecretKeySpec(jwtSecret.getBytes(), algorithm.getValue());
        return NimbusJwtDecoder.withSecretKey(key).build();
    }
}