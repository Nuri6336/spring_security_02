package com.spring.securityPractice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomExceptionHandler.class)
    public ResponseEntity<?> handleDataNotExists(CustomExceptionHandler dataNotFound)
    {
        return new ResponseEntity<>(dataNotFound.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
