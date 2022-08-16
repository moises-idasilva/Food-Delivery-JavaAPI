package com.moises.foodapp.infrastructure.service.storage;

public class StorageException extends RuntimeException{

    private static final long serialVersionUID = 3086366803003334964L;

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }

}