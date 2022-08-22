package com.moises.foodapp.infrastructure.service.email;

public class EmailException extends RuntimeException{

    private static final long serialVersionUID = 3086366803003334964L;

    public EmailException(String message) {
        super(message);
    }

    public EmailException(String message, Throwable cause) {
        super(message, cause);
    }

}