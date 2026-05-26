package com.mabadcortes.taskmanager.exception;

public class TaskNotFoundException extends RuntimeException {

    /*
     * Create a new exception with a custom error message.
     */
    public TaskNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
