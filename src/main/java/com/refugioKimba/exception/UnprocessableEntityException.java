package com.refugioKimba.exception;

public class UnprocessableEntityException extends RuntimeException {
  private static final String DESCRIPCION = "Unprocessable Entity (422)";
  public UnprocessableEntityException(String message) {
        super(DESCRIPCION + ". " + message);
    }
}
