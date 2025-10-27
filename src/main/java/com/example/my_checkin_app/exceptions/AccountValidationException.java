package com.example.my_checkin_app.exceptions;

public class AccountValidationException extends RuntimeException {
    public AccountValidationException() {
        super("Username/Email can not be empty and password length must be greater than 3");
    }
}