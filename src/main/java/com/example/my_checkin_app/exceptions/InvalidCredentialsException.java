package com.example.my_checkin_app.exceptions;

public class InvalidCredentialsException extends RuntimeException {
        public InvalidCredentialsException() {
        super("Invalid credentials provided.");
    }

    public InvalidCredentialsException(String username) {
        super("The username '" + username + "' and password provided is invalid");
    }
}