package com.refugioKimba.exception;

public class TooManyRequestsException extends RuntimeException {
    private static final String DESCRIPCION = "Too Many Requests (429)";
    public TooManyRequestsException(String message) {
        super(DESCRIPCION + ". " + message);
    }
}
