package com.refugioKimba.exception;

public class ForbiddenException extends RuntimeException {
    private static final String DESCRIPCION = "Forbidden (403)";
    public ForbiddenException(String message) {super(DESCRIPCION + ". " + message);}
}
