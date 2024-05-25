package com.example.bankapplication.dao.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bank_account")
@Builder
public class BankAccount {

    @Id
    @GeneratedValue
    private Long id;
    private double balance;

    @OneToOne(mappedBy = "initialBalance") // mappedBy indicates the inverse side of the relationship
    private User user;
}
