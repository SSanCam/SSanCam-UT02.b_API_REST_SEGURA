package com.refugioKimba.exception;

/**
 * Excepción lanzada cuando un formato de dato no es válido.
 */
public class InvalidFormatException extends RuntimeException {

    private static final String DESCRIPCION = "Invalid Format (400)";

    /**
     * Constructor para la excepción InvalidFormatException.
     *
     * @param mensaje el mensaje adicional que describe el detalle del error de formato.
     */
    public InvalidFormatException(String mensaje) {
        super(DESCRIPCION + ". " + mensaje);
    }
}