package com.api.jukeboxd.core.exception;

import com.api.jukeboxd.core.exception.model.Error;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

import java.sql.SQLException;

public class ValidationException extends DataIntegrityViolationException {
    private final Error error;

    public ValidationException(HttpStatus status, String message) {
        super(message);
        this.error = new Error(status, message);
    }

    public Error getErrorMessage() {
        return error;
    }
}