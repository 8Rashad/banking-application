package com.example.bankapplication;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bank_account")
public class BankAccount {

    @Id
    private Long id;
    private double balance;

    public void deposit(double amount) {

        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            System.out.println("Not enough balance...");
            return false;
        }
    }
}
