package com.restaurant_system.GastroManager.security.jwt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

public class JwtEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        System.out.println("ENTRY POINT:" +
                        " " + request.getMethod() +
                        " " + request.getServletPath() +
                        " " + authException.getMessage() +
                        " " + authException.getClass() +
                        " " + authException.getAuthenticationRequest()
                );
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED ,authException.getMessage());
    }
}
