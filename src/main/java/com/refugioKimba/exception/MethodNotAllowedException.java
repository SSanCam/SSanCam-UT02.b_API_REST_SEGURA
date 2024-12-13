package com.refugioKimba.exception;

public class MethodNotAllowedException extends RuntimeException {
    private static final String DESCRIPCION = "Method Not Allowed (405)";
    public MethodNotAllowedException(String message) {super(DESCRIPCION + ". " + message);}
}
