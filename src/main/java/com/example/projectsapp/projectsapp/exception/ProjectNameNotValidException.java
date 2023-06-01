package com.example.projectsapp.projectsapp.exception;

import static com.example.projectsapp.projectsapp.exception.ApiExceptionHandler.PROJECT_NAME_NOT_VALID_MESSAGE;

public class ProjectNameNotValidException extends RuntimeException {
    public ProjectNameNotValidException() {}

    @Override
    public String getMessage() {
        return PROJECT_NAME_NOT_VALID_MESSAGE;
    }
}
