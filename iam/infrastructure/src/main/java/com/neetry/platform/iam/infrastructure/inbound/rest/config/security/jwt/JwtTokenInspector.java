package com.neetry.platform.iam.infrastructure.inbound.rest.config.security.jwt;

import com.neetry.platform.iam.domain.oauth.jwt.JwtClaim;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OAuth2IntrospectionException;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenInspector implements OpaqueTokenIntrospector {

    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public JwtTokenInspector(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public OAuth2AuthenticatedPrincipal introspect(String token) {
        try {
            jwtTokenUtil.validateToken(token);
            return new DefaultOAuth2AuthenticatedPrincipal(
                    jwtTokenUtil.getUsernameFromToken(token),
                    getAttributes(token),
                    jwtTokenUtil.extractGrantedAuthorities(token));
        } catch (JwtException e) {
            throw new OAuth2IntrospectionException("Invalid JWT token", e);
        }
    }

    private Map<String, Object> getAttributes(String token) {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put(JwtClaim.SUB, jwtTokenUtil.getClaimFromToken(token, JwtClaim.SUB, String.class));
        attributes.put(JwtClaim.EXPIRATION, jwtTokenUtil.getExpirationDateFromToken(token));
        attributes.put(JwtClaim.ID, jwtTokenUtil.getClaimFromToken(token, JwtClaim.ID, String.class));
        attributes.put(JwtClaim.EMAIL, jwtTokenUtil.getClaimFromToken(token, JwtClaim.EMAIL, String.class));
        attributes.put(JwtClaim.FIRST_NAME, jwtTokenUtil.getClaimFromToken(token, JwtClaim.FIRST_NAME, String.class));
        attributes.put(JwtClaim.LAST_NAME, jwtTokenUtil.getClaimFromToken(token, JwtClaim.LAST_NAME, String.class));
        return attributes;
    }
}

