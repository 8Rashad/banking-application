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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double balance;

    @OneToOne(mappedBy = "initialBalance") // mappedBy indicates the inverse side of the relationship
    private User user;


}

/* "username": "Vahid",
         "password": "9271010",
         "fullName":"Vahid Ismayilzade",
         "dateOfBirth":"2000-12-05T00:00:00Z",
         "email": "vahid.ismayilzada@gmail.com",
         "phone": "+994517391339",
         "initialBalance": {
         "balance": 350.0
         } */