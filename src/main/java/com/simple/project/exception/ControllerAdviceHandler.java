package com.simple.project.exception;

import com.simple.project.exception.dto.CustomExceptionResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ControllerAdviceHandler {

    @ExceptionHandler({CustomException.class})
    ResponseEntity<CustomExceptionResponseDTO> throwCustomException(HttpClientErrorException exception, HttpServletRequest request) {

        CustomExceptionResponseDTO customExceptionResponseDTO = new CustomExceptionResponseDTO();
        customExceptionResponseDTO.setStatus(exception.getStatusCode().value());
        customExceptionResponseDTO.setTitle(exception.getStatusCode().toString().substring(4));
        customExceptionResponseDTO.setUrl(String.valueOf(request.getRequestURL()));
        customExceptionResponseDTO.setMessage(exception.getStatusText());

        return new ResponseEntity<>(customExceptionResponseDTO,exception.getStatusCode());
    }
}
