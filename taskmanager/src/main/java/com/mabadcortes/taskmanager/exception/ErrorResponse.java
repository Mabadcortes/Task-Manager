package com.mabadcortes.taskmanager.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    /*
     * HTTP status code.
     */
    private int status;

    /*
     * General error message.
     */
    private String message;

    /*
     * Specific validation errors.
     */
    private Map<String, String> errors;

}
