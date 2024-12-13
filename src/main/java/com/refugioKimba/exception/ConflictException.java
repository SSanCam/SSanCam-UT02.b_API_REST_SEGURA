package com.refugioKimba.exception;

public class ConflictException extends RuntimeException {
    private static final String DESCRIPCION = "Conflict (409)";
    public ConflictException(String message) {
        super(DESCRIPCION + ". " + message);
    }
}
