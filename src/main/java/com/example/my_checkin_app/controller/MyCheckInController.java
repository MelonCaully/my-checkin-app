package com.example.my_checkin_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.my_checkin_app.entity.*;
import com.example.my_checkin_app.service.*;

@RestController
@RequestMapping("my-checkin")
public class MyCheckInController {
    private final AccountService accountService;
    
    @Autowired
    public MyCheckInController(AccountService accountService) {
        this.accountService = accountService;
    }

    // endpoint for creating account
    @PostMapping("register")
    public ResponseEntity<Account> handlerCreateAccount(@RequestBody Account account) {
        Account registered = accountService.createAccount(account);
        return ResponseEntity.status(HttpStatus.OK).body(registered);
    }
} 
