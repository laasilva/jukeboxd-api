package com.api.jukeboxd.core.exception;

import com.api.jukeboxd.core.exception.model.Error;
import org.springframework.http.HttpStatus;

import java.security.NoSuchAlgorithmException;

public class PasswordEncryptionException extends NoSuchAlgorithmException {
    private final Error error;

    public PasswordEncryptionException(HttpStatus status, String message) {
        super();
        this.error = new Error(status, message);
    }

    public PasswordEncryptionException(String message) {
        super();
        this.error = new Error(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    public Error getErrorMessage() {
        return error;
    }
}