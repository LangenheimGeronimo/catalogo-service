package com.example.catalogoservice.exception;

public class InvalidStateException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "El estado proporcionado no es válido";

    public InvalidStateException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidStateException(String message) {
        super(message);
    }
}