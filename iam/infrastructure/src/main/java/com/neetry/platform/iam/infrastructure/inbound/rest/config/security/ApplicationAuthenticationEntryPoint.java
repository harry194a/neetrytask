package com.neetry.platform.iam.infrastructure.inbound.rest.config.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ApplicationAuthenticationEntryPoint implements AuthenticationEntryPoint {

    public ApplicationAuthenticationEntryPoint() {
    }
    
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.sendError(401, "Unauthorized");
    }
}
