package com.example.projectsapp.projectsapp.exception;

import static com.example.projectsapp.projectsapp.exception.ApiExceptionHandler.USER_NAME_NOT_FOUND;

public class UserNameNotFoundException extends RuntimeException {
    private String userName;

    public UserNameNotFoundException(String name) {
        this.userName = name;
    }

    @Override
    public String getMessage() {
        return String.format(USER_NAME_NOT_FOUND, userName);
    }
}
