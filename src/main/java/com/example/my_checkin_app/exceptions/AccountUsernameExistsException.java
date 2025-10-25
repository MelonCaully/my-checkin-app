package com.example.my_checkin_app.exceptions;

public class AccountUsernameExistsException extends RuntimeException {
    public AccountUsernameExistsException(String username) {
        super("Account already exists with username: " + username);
    }
}