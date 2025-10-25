package com.example.my_checkin_app.exceptions;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String username) {
        super("Account not found for username: " + username);
    }
}