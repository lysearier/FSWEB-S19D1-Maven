package com.workintech.s18d2.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(PlantException.class)
    public ResponseEntity<PlantErrorResponse> handlePlantException(PlantException ex) {
        log.error("PlantException occurred: {}", ex.getMessage());

        PlantErrorResponse errorResponse = new PlantErrorResponse(
                ex.getMessage(),
                ex.getHttpStatus().value(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<PlantErrorResponse> handleGenericException(Exception ex) {
        log.error("Unexpected error occurred: {}", ex.getMessage(), ex);

        PlantErrorResponse errorResponse = new PlantErrorResponse(
                "An unexpected error occurred",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
