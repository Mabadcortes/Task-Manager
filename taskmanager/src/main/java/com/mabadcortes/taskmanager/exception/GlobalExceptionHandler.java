package com.mabadcortes.taskmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
     * Handles TaskNotFoundException errors.
     * Returns a custom 404 NOT FOUND response.
     */
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTaskNotFoundException(
            TaskNotFoundException exception) {

        /*
         * Creates a custom error response.
         */
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                null
        );

        /*
         * Returns the custom error response with HTTP status 404.
         */
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    /*
     * Handles validation errors triggered by @Valid.
     * Returns a custom 400 BAD REQUEST response.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException exception) {

        /*
         * Map used to store validation errors while preserving insertion order.
         */
        Map<String, String> errors = new LinkedHashMap<>();

        /*
         * Extracts all validation errors.
         */
        exception.getBindingResult().getFieldErrors().forEach(error -> {

            /*
             * Saves:
             * field name -> error message.
             */
            errors.put(error.getField(), error.getDefaultMessage());
        });

        /*
         * Creates a custom validation error response.
         */
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed",
                errors
        );

        /*
         * Returns the custom error response with HTTP status 400.
         */
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }
}