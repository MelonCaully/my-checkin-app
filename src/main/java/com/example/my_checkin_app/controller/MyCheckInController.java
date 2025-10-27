package com.example.my_checkin_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.my_checkin_app.entity.*;
import com.example.my_checkin_app.service.*;
import com.example.my_checkin_app.util.JwtUtil;

@RestController
@RequestMapping("my-checkin")
@CrossOrigin(origins = "http://localhost:5173") // your frontend URL
public class MyCheckInController {
    private final AccountService accountService;
    private JwtUtil jwtUtil;
    
    @Autowired
    public MyCheckInController(AccountService accountService, JwtUtil jwtUtil) {
        this.accountService = accountService;
        this.jwtUtil = jwtUtil;
    }

    // endpoint for creating account
    @PostMapping("register")
    public ResponseEntity<Account> handlerCreateAccount(@RequestBody Account account) {
        Account registered = accountService.createAccount(account);
        return ResponseEntity.status(HttpStatus.OK).body(registered);
    }

    // endpoint for logging in
    @PostMapping("login")
    public ResponseEntity<String> handlerLoginAccount(@RequestBody Account account) {
        Account login = accountService.loginAccount(account.getEmail(), account.getPassword());
        String token = jwtUtil.generateToken(login.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
} 
