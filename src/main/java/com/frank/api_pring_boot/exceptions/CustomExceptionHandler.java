package com.frank.api_pring_boot.exceptions;

import com.frank.api_pring_boot.errors.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class CustomExceptionHandler {

    //Ici on capture tous les exception de type EtudiantNotFoundException. On peut ajouter d'autre exception.
    @ExceptionHandler(EtudiantNotFoundException.class)
    public ResponseEntity<ApiError> handleEtudiantNotFoundException(EtudiantNotFoundException e) {
        ApiError apiError = new ApiError();
        apiError.setMessage(e.getMessage());
        apiError.setCode(HttpStatus.NOT_FOUND.value());
        apiError.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    //Pour toutes autre type exception. Autant d'exception qu'on a on peut definir autant d'exceptionHandler.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception e) {
        ApiError apiError = new ApiError();
        apiError.setMessage(e.getMessage());
        apiError.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        apiError.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
