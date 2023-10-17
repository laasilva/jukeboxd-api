package com.api.jukeboxd.application.controller;

import com.api.jukeboxd.core.exception.PasswordEncryptionException;
import com.api.jukeboxd.core.exception.model.Error;
import jakarta.xml.bind.ValidationException;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> conflict(DataIntegrityViolationException e) {

        String message = NestedExceptionUtils.getMostSpecificCause(e).getMessage();
        Error error = new Error(HttpStatus.CONFLICT, message);
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(PasswordEncryptionException.class)
    public ResponseEntity<?> conflict(PasswordEncryptionException e) {

        String message = NestedExceptionUtils.getMostSpecificCause(e).getMessage();
        Error error = new Error(HttpStatus.UNPROCESSABLE_ENTITY, message);
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> conflict(ValidationException e) {

        String message = NestedExceptionUtils.getMostSpecificCause(e).getMessage();
        Error error = new Error(HttpStatus.UNPROCESSABLE_ENTITY, message);
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
