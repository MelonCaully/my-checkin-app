package com.example.my_checkin_app.service;

import com.example.my_checkin_app.repository.AccountRepository;
import com.example.my_checkin_app.entity.Account;
import com.example.my_checkin_app.exceptions.*;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account) {
        if (account.getUsername().isBlank() || account.getPassword().length() < 4) {
            throw new AccountValidationException();
        }
        if (accountRepository.findByUsername(account.getUsername()).isPresent()) {
            throw new AccountUsernameExistsException(account.getUsername());
        }
        return accountRepository.save(account);
    }

    public Account loginAccount(String username, String password) {
        return accountRepository.findByUsernameAndPassword(username, password)
            .orElseThrow(() -> new InvalidCredentialsException(username));
    }
}
