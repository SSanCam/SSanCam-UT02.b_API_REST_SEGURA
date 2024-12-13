package com.refugioKimba.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Clase para el manejo centralizado de excepciones en la API.
 * Permite capturar excepciones personalizadas y nativas, devolviendo respuestas consistentes
 * al cliente con códigos de estado HTTP adecuados.
 */
@ControllerAdvice
public class APIExceptionHandler {

    /**
     * Maneja excepciones de tipo {@link BadRequestException} y {@link IllegalArgumentException}.
     * Devuelve un código de estado 400 Bad Request con un mensaje descriptivo.
     *
     * @param request La solicitud HTTP donde ocurrió la excepción.
     * @param e       La excepción lanzada.
     * @return Un objeto {@link ErrorMessageForClient} con el mensaje y URI de la solicitud.
     */
    @ExceptionHandler({BadRequestException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessageForClient handleBadRequest(HttpServletRequest request, Exception e) {
        return new ErrorMessageForClient(e.getMessage(), request.getRequestURI());
    }

    /**
     * Maneja excepciones de tipo {@link InvalidFormatException}.
     * Devuelve un código de estado 400 Bad Request con un mensaje descriptivo.
     *
     * @param request La solicitud HTTP donde ocurrió la excepción.
     * @param e       La excepción lanzada.
     * @return Un objeto {@link ErrorMessageForClient} con el mensaje y URI de la solicitud.
     */
    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorMessageForClient handlerInvalidFormat(HttpServletRequest request, InvalidFormatException e) {
        return new ErrorMessageForClient(e.getMessage(), request.getRequestURI());
    }

    /**
     * Maneja excepciones de tipo {@link EntityNotFoundException}.
     * Devuelve un código de estado 404 Not Found con un mensaje descriptivo.
     *
     * @param request La solicitud HTTP donde ocurrió la excepción.
     * @param e       La excepción lanzada.
     * @return Un objeto {@link ErrorMessageForClient} con el mensaje y URI de la solicitud.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessageForClient handlerNotFound(HttpServletRequest request, EntityNotFoundException e) {
        return new ErrorMessageForClient(e.getMessage(), request.getRequestURI());
    }

    /**
     * Maneja excepciones de tipo {@link GeneralException}.
     * Devuelve un código de estado 500 Internal Server Error con un mensaje descriptivo.
     *
     * @param request La solicitud HTTP donde ocurrió la excepción.
     * @param e       La excepción lanzada.
     * @return Un objeto {@link ErrorMessageForClient} con el mensaje y URI de la solicitud.
     */
    @ExceptionHandler(GeneralException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessageForClient handlerGeneralException(HttpServletRequest request, GeneralException e) {
        return new ErrorMessageForClient(e.getMessage(), request.getRequestURI());
    }

    /**
     * Maneja excepciones de tipo {@link InternalServerErrorException}.
     * Devuelve un código de estado 500 Internal Server Error con un mensaje descriptivo.
     *
     * @param request La solicitud HTTP donde ocurrió la excepción.
     * @param e       La excepción lanzada.
     * @return Un objeto {@link ErrorMessageForClient} con el mensaje y URI de la solicitud.
     */
    @ExceptionHandler(InternalServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessageForClient handlerInternalServerError(HttpServletRequest request, Exception e) {
        return new ErrorMessageForClient(e.getMessage(), request.getRequestURI());
    }
    /**
     * Maneja cualquier excepción no manejada específicamente.
     * Devuelve un código de estado 500 Internal Server Error con un mensaje genérico.
     *
     * @param request La solicitud HTTP donde ocurrió la excepción.
     * @param e       La excepción lanzada.
     * @return Un objeto {@link ErrorMessageForClient} con el mensaje genérico y URI de la solicitud.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessageForClient handleGlobalException(HttpServletRequest request, Exception e) {
        return new ErrorMessageForClient("Ocurrió un error inesperado. Por favor, intente más tarde.", request.getRequestURI());
    }
}
