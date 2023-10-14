package com.simple.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class CustomException extends RuntimeException{
    public CustomException() {
    }

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    public CustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CustomException(HttpClientErrorException httpClientErrorException) {
        super(httpClientErrorException);
    }

    public CustomException(HttpStatus httpStatus, String errorMessage) {
        super(new HttpClientErrorException(httpStatus, errorMessage));
    }
}
