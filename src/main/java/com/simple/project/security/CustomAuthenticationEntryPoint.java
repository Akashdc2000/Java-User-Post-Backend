package com.simple.project.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simple.project.exception.dto.CustomExceptionResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    public CustomAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {

        // Set CORS headers
        response.setHeader("Access-Control-Allow-Origin", "*"); // Replace with your allowed origins
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");

        // Set response status and write error message
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        CustomExceptionResponseDTO customExceptionResponseDTO = new CustomExceptionResponseDTO();
        customExceptionResponseDTO.setStatus(HttpStatus.UNAUTHORIZED.value());
        customExceptionResponseDTO.setTitle(HttpStatus.UNAUTHORIZED.name());
        customExceptionResponseDTO.setUrl(String.valueOf(request.getRequestURL()));
        customExceptionResponseDTO.setMessage(authException.getMessage());

        try (PrintWriter out = response.getWriter()) {
            String json = objectMapper.writeValueAsString(customExceptionResponseDTO);
            out.print(json);
            out.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
