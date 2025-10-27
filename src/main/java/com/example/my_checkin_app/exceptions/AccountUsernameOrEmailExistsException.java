package com.example.my_checkin_app.exceptions;

public class AccountUsernameOrEmailExistsException extends RuntimeException {
    public AccountUsernameOrEmailExistsException(String username, String email) {
        super("Account already exists with username '" + username + "' or email '" + email +"'");
    }
}