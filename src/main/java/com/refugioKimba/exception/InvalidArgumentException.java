package com.refugioKimba.exception;

/**
 * Excepción personalizada para manejar argumentos no válidos.
 */
public class InvalidArgumentException extends RuntimeException {
    private static final String DESCRIPCION = "Invalid Argument (400)";

    public InvalidArgumentException(String mensaje) {
        super(DESCRIPCION + ". " + mensaje);
    }
}