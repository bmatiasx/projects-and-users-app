package com.example.projectsapp.projectsapp.exception;

import static com.example.projectsapp.projectsapp.exception.ApiExceptionHandler.PROJECTS_NOT_LOADED_MESSAGE;

public class ProjectsNotLoadedException extends RuntimeException{
    public ProjectsNotLoadedException() {}

    @Override
    public String getMessage() {
        return PROJECTS_NOT_LOADED_MESSAGE;
    }
}
