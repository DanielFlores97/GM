package com.test.backend.exception;

public class NotFoundPersonasException extends RuntimeException {
    public NotFoundPersonasException(String message) {
        super(message);
    }
}