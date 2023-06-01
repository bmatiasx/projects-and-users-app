package com.example.projectsapp.projectsapp.exception;

import static com.example.projectsapp.projectsapp.exception.ApiExceptionHandler.PROJECT_NOT_FOUND_MESSAGE;

public class ProjectNotFoundException extends RuntimeException{
    public ProjectNotFoundException(){}

    @Override
    public String getMessage() {
        return PROJECT_NOT_FOUND_MESSAGE;
    }
}
