package com.example.projectsapp.projectsapp.exception;

import static com.example.projectsapp.projectsapp.exception.ApiExceptionHandler.USER_ID_NOT_FOUND;

public class UserIdNotFoundException extends RuntimeException {
    private long userId;

    public UserIdNotFoundException(long id) {
        this.userId = id;
    }

    @Override
    public String getMessage() {
        return String.format(USER_ID_NOT_FOUND, userId);
    }
}
