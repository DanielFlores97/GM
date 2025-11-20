package com.test.backend.exception;

public class NotFoundFacturasException extends RuntimeException {
    public NotFoundFacturasException(String message) {
        super(message);
    }
}