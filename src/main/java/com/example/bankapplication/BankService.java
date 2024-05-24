package com.example.bankapplication;

import org.springframework.stereotype.Service;

@Service
public class BankService {

    public void deposit(BankAccount account, double amount) {
        double currentBalance = account.getBalance();
        currentBalance += amount;
        account.setBalance(currentBalance);
    }

    public boolean withdraw(BankAccount account,double amount) {
        double currentBalance = account.getBalance();
        if (currentBalance >= amount) {
            currentBalance -= amount;
            account.setBalance(currentBalance);
            return true;
        } else {
            System.out.println("Not enough balance...");
            return false;
        }
    }
}
