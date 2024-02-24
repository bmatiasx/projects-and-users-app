package com.example.projectsapp.exception;

public class UsersNotCreatedException extends RuntimeException {
    public UsersNotCreatedException() {
    }

    @Override
    public String getMessage() {
        return ApiExceptionHandler.USERS_NOT_CREATED_MESSAGE;
    }
}
