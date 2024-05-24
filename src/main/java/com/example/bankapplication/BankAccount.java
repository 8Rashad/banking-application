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

}
