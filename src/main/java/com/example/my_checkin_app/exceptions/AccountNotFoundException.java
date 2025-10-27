package com.example.my_checkin_app.exceptions;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String email) {
        super("Account not found for email: " + email);
    }
}