package com.neetry.platform.iam.infrastructure.inbound.rest.config.security;

import com.neetry.platform.iam.domain.oauth.jwt.JwtClaim;
import com.neetry.platform.iam.domain.oauth.jwt.JwtUserInfo;
import com.neetry.platform.iam.infrastructure.inbound.rest.config.security.jwt.JwtTokenInspector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.context.annotation.RequestScope;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Objects;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private static final String JWT_ALGORITHM = "HmacSHA256";

    private final JwtTokenInspector jwtTokenIntrospector;
    private final String jwtSecret;

    public SecurityConfig(
            JwtTokenInspector jwtTokenIntrospector,
            @Value("${neetry.platform.iam.jwt-secret}") String jwtSecret) {
        this.jwtTokenIntrospector = jwtTokenIntrospector;
        this.jwtSecret = jwtSecret;
    }


    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder(10, new SecureRandom());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.authorizeHttpRequests(
                        authorizeHttpRequests -> authorizeHttpRequests
                                .requestMatchers("/v3/api-docs/**", "/swagger*/**").permitAll()
                                .requestMatchers(
                                        "/oauth/public/**",
                                        "/oauth/token", "/webjars/**",
                                        "/api/contact-us/message").permitAll()
                                .requestMatchers("/oauth/private/**").hasAuthority("SUPER_ADMIN")
                                .anyRequest().authenticated()
                ).csrf(AbstractHttpConfigurer::disable)
                .oauth2ResourceServer(resourceServer -> resourceServer.opaqueToken(token -> token.introspector(jwtTokenIntrospector)))
                .build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        SecretKey key = new SecretKeySpec(jwtSecret.getBytes(), JWT_ALGORITHM);
        return NimbusJwtDecoder.withSecretKey(key).build();
    }

    @Bean
    @RequestScope
    public CurrentUser currentUser() {
        Authentication authentication = SecurityContextHolder
                .getContext()
                .getAuthentication();
        if (Objects.isNull(authentication)) {
            return null;
        }
        OAuth2AuthenticatedPrincipal oAuth2Principal = (OAuth2AuthenticatedPrincipal) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
                JwtUserInfo jwtUserInfo = new JwtUserInfo(
                Long.valueOf(Objects.requireNonNull(oAuth2Principal.getAttribute(JwtClaim.ID))),
                oAuth2Principal.getAttribute(JwtClaim.EMAIL),
                oAuth2Principal.getAttribute(JwtClaim.FIRST_NAME),
                oAuth2Principal.getAttribute(JwtClaim.LAST_NAME),
                oAuth2Principal.getAttribute(JwtClaim.AUTHORITIES));
        return () -> jwtUserInfo;
    }
}