package com.example.my_checkin_app.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.example.my_checkin_app.entity.Account;
import com.example.my_checkin_app.exceptions.AccountNotFoundException;
import com.example.my_checkin_app.repository.AccountRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    @Autowired
    public CustomUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws AccountNotFoundException {
        Account account = accountRepository.findByUsername(username)
            .orElseThrow(() -> new AccountNotFoundException(username));

        return new org.springframework.security.core.userdetails.User(
            account.getUsername(),
            account.getPassword(),
            new ArrayList<>()
        );
    }
}

