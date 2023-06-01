package com.example.projectsapp.projectsapp.exception;

import static com.example.projectsapp.projectsapp.exception.ApiExceptionHandler.PROJECT_WITHDRAW_USERS_NOT_FOUND_MESSAGE;

public class ProjectWithdrawException extends RuntimeException {
    private final String ids;

    public ProjectWithdrawException(String ids) {
        this.ids = ids;
    }

    @Override
    public String getMessage() {
        return String.format(PROJECT_WITHDRAW_USERS_NOT_FOUND_MESSAGE, ids);
    }
}
