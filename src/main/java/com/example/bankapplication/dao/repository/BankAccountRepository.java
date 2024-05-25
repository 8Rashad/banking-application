package com.example.bankapplication.dao.repository;

import com.example.bankapplication.dao.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
}
