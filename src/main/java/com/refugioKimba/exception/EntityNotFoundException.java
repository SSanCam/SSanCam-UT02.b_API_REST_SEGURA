package com.refugioKimba.exception;

public class EntityNotFoundException extends RuntimeException {
    private static final String DESCRIPCION = "Entity not found (404)";

    public EntityNotFoundException(String mensaje) {
        super(DESCRIPCION + ". " + mensaje);
    }
}
