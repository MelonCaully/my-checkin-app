package com.example.my_checkin_app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    // handles thrown AccountValidationExceptions
    @ExceptionHandler(AccountValidationException.class)
    public ResponseEntity<String> handleAccountValidation(AccountValidationException ex) {
        return ResponseEntity
              .status(HttpStatus.BAD_REQUEST)
              .body(ex.getMessage());
    }

    // handles thrown AccountUsernameExistsExceptions
    @ExceptionHandler(AccountUsernameOrEmailExistsException.class)
    public ResponseEntity<String> handlerAccountUsernameExists(AccountUsernameOrEmailExistsException ex) {
        return ResponseEntity
              .status(HttpStatus.CONFLICT)
              .body(ex.getMessage());
    }

    // handles thrown InvalidCredentialsExceptions
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> handlerInvalidCredentials(InvalidCredentialsException ex) {
        return ResponseEntity
              .status(HttpStatus.UNAUTHORIZED)
              .body(ex.getMessage());
    }
}
