package com.example.projectsapp.projectsapp.exception;

import static com.example.projectsapp.projectsapp.exception.ApiExceptionHandler.USER_EMAIL_ALREADY_EXISTS;

public class UserEmailExistsException extends RuntimeException {

    public UserEmailExistsException() {
    }

    @Override
    public String getMessage() {
        return USER_EMAIL_ALREADY_EXISTS;
    }
}
