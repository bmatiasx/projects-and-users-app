package com.example.projectsapp.projectsapp.exception;

import static com.example.projectsapp.projectsapp.exception.ApiExceptionHandler.PROJECT_ASSIGN_USERS_NOT_FOUND_MESSAGE;

public class ProjectAssignException extends RuntimeException {
    private final String ids;

    public ProjectAssignException(String ids) {
        this.ids = ids;
    }

    @Override
    public String getMessage() {
        return String.format(PROJECT_ASSIGN_USERS_NOT_FOUND_MESSAGE, ids);
    }
}
