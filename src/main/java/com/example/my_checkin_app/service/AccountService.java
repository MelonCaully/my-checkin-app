package com.example.my_checkin_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.my_checkin_app.entity.Account;
import com.example.my_checkin_app.repository.AccountRepository;
import com.example.my_checkin_app.exceptions.AccountValidationException;
import com.example.my_checkin_app.exceptions.AccountUsernameOrEmailExistsException;
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
        if (account.getUsername().isBlank() || account.getPassword().length() < 4 || account.getEmail().isBlank()) {
            throw new AccountValidationException();
        }
        if (accountRepository.findByUsername(account.getUsername()).isPresent() || accountRepository.findByEmail(account.getEmail()).isPresent()) {
            throw new AccountUsernameOrEmailExistsException(account.getUsername(), account.getEmail());
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    public Account loginAccount(String email, String password) {
        Account account = accountRepository.findByEmail(email)
                .orElseThrow(() -> new AccountNotFoundException(email));

        if (!passwordEncoder.matches(password, account.getPassword())) {
            throw new InvalidCredentialsException(email);
        }
        return account;
    }
}
