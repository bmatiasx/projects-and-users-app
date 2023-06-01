package com.example.projectsapp.projectsapp.exception;

import static com.example.projectsapp.projectsapp.exception.ApiExceptionHandler.USER_EMAIL_NOT_FOUND;

public class UserEmailNotFoundException extends  RuntimeException{
    private String email;

    public UserEmailNotFoundException(String email) {
        this.email = email;
    }

    @Override
    public String getMessage() {
        return USER_EMAIL_NOT_FOUND;
    }
}
