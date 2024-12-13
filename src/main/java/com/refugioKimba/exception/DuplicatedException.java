package com.refugioKimba.exception;

public class DuplicatedException extends RuntimeException {
  private static final String DESCRIPCION = "Duplicate resource error (409)";
  public DuplicatedException(String message) {
    super(DESCRIPCION + ". " + message);
  }
}
