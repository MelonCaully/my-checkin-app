package com.example.my_checkin_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.my_checkin_app.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsername(String username);
    Optional<Account> findByEmail(String email);
}
