package com.example.projectsapp.projectsapp.exception;

import static com.example.projectsapp.projectsapp.exception.ApiExceptionHandler.PROJECT_UNASSIGN_USERS_NOT_FOUND_MESSAGE;

public class ProjectUnassignException extends RuntimeException {
    private final String ids;

    public ProjectUnassignException(String ids) {
        this.ids = ids;
    }

    @Override
    public String getMessage() {
        return String.format(PROJECT_UNASSIGN_USERS_NOT_FOUND_MESSAGE, ids);
    }
}
