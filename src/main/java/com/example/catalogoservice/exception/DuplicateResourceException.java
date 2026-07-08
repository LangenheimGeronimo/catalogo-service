package com.example.catalogoservice.exception;

public class DuplicateResourceException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "El recurso ya existe";

    public DuplicateResourceException() {
        super(DEFAULT_MESSAGE);
    }

    public DuplicateResourceException(String message) {
        super(message);
    }
}