package com.api.jukeboxd.core.exception.model;

import org.springframework.http.HttpStatus;

public enum ErrorMessage {
    USER_NOT_FOUND_USERNAME(HttpStatus.NOT_FOUND, "Username doesn't exist in the database."),
    USER_NOT_FOUND_ID(HttpStatus.NOT_FOUND, "Id doesn't exist in the database."),
    USER_PASSWORD_INCORRECT(HttpStatus.UNPROCESSABLE_ENTITY, "Incorrect password for matched user."),
    USER_EXISTS(HttpStatus.CONFLICT, "User already exists."),
    EMAIL_EXISTS(HttpStatus.CONFLICT, "Email address already in use."),
    USER_PASSWORD_NOT_A_MATCH(HttpStatus.CONFLICT, "User and/or password incorrect."),
    EMPTY_USER(HttpStatus.UNPROCESSABLE_ENTITY, "User cannot be empty."),
    EMPTY_EMAIL(HttpStatus.UNPROCESSABLE_ENTITY, "Email cannot be empty."),
    EMPTY_PASSWORD(HttpStatus.UNPROCESSABLE_ENTITY, "Password cannot be empty."),
    INVALID_EMAIL_PATTERN(HttpStatus.UNPROCESSABLE_ENTITY, "Email pattern is invalid."),
    INVALID_PASSWORD_PATTERN(HttpStatus.UNPROCESSABLE_ENTITY, "Password must contain at least 8 characters, " +
            "containing at least one letter, one number and one special character.");

    private final HttpStatus code;
    private final String message;

    ErrorMessage(HttpStatus code, String message) {
        this.code = code;
        this.message = message;
    }

    public HttpStatus getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}