package com.refugioKimba.exception;

/**
 * Excepción general para capturar errores no específicos en la aplicación.
 * Puede utilizarse como una excepción de propósito general cuando no hay una excepción específica disponible.
 */
public class GeneralException extends Exception {

    private static final String DESCRIPCION = "General Application Error";

    /**
     * Constructor para la excepción GeneralException.
     *
     * @param mensaje el mensaje adicional que describe el detalle del error.
     */
    public GeneralException(String mensaje) {
        super(DESCRIPCION + ". " + mensaje);
    }
}