package com.example.my_checkin_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.my_checkin_app.entity.Account;
import com.example.my_checkin_app.repository.AccountRepository;
import com.example.my_checkin_app.exceptions.AccountValidationException;
import com.example.my_checkin_app.exceptions.AccountUsernameExistsException;
import com.example.my_checkin_app.exceptions.InvalidCredentialsException;
import com.example.my_checkin_app.exceptions.AccountNotFoundException;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Account createAccount(Account account) {
        if (account.getUsername().isBlank() || account.getPassword().length() < 4) {
            throw new AccountValidationException();
        }
        if (accountRepository.findByUsername(account.getUsername()).isPresent()) {
            throw new AccountUsernameExistsException(account.getUsername());
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    public Account loginAccount(String username, String password) {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new AccountNotFoundException(username));

        if (!passwordEncoder.matches(password, account.getPassword())) {
            throw new InvalidCredentialsException();
        }
        return account;
    }
}
