package com.spring.securityPractice.exception;

public class CustomExceptionHandler extends Exception{

    private  String message;
    public CustomExceptionHandler(String message) {
        super(message);
    }
}
