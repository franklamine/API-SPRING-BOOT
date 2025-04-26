package com.frank.api_pring_boot.exceptions;

import com.frank.api_pring_boot.errors.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice // Rend cette classe capable d'intercepter les exceptions de manière globale dans l'application
public class CustomExceptionHandler {

    // Capture les exceptions spécifiques de type EtudiantNotFoundException
    @ExceptionHandler(EtudiantNotFoundException.class)
    public ResponseEntity<ApiError> handleEtudiantNotFoundException(EtudiantNotFoundException e) {
        ApiError apiError = new ApiError();
        apiError.setMessage(e.getMessage()); // Message personnalisé de l'exception
        apiError.setCode(HttpStatus.NOT_FOUND.value()); // Code HTTP 404
        apiError.setTimestamp(LocalDateTime.now()); // Date et heure de l'erreur
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    // Capture toutes les autres exceptions non gérées explicitement
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception e) {
        ApiError apiError = new ApiError();
        apiError.setMessage(e.getMessage()); // Message générique de l'exception
        apiError.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value()); // Code HTTP 500
        apiError.setTimestamp(LocalDateTime.now()); // Date et heure de l'erreur
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
