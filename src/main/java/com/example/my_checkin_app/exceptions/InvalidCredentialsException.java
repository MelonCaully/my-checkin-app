package com.example.my_checkin_app.exceptions;

public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException(String email) {
        super("The username '" + email + "' and password provided is invalid");
    }
}