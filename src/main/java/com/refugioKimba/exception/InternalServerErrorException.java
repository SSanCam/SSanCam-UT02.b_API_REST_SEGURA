package com.refugioKimba.exception;

/**
 * Excepción lanzada cuando ocurre un error interno en el servidor.
 */
public class InternalServerErrorException extends RuntimeException {

    private static final String DESCRIPCION = "Internal Server Error (500)";

    /**
     * Constructor para la excepción InternalServerErrorException.
     *
     * @param mensaje el mensaje adicional que describe el detalle del error interno.
     */
    public InternalServerErrorException(String mensaje) {
        super(DESCRIPCION + ". " + mensaje);
    }
}