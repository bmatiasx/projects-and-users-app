package com.example.projectsapp.exception;

import static com.example.projectsapp.exception.ApiExceptionHandler.USER_EMAIL_NOT_VALID;

public class UserEmailNotValidException extends RuntimeException {

    public UserEmailNotValidException() {
    }

    @Override
    public String getMessage() {
        return USER_EMAIL_NOT_VALID;
    }
}
