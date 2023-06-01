package com.example.projectsapp.projectsapp.exception;

import static com.example.projectsapp.projectsapp.exception.ApiExceptionHandler.USERS_NOT_CREATED_MESSAGE;

public class UsersNotCreatedException extends RuntimeException {
    public UsersNotCreatedException() {
    }

    @Override
    public String getMessage() {
        return USERS_NOT_CREATED_MESSAGE;
    }
}
