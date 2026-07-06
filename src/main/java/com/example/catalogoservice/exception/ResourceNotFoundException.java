package com.example.catalogoservice.exception;

public class ResourceNotFoundException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "El recurso solicitado no existe";

    public ResourceNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}