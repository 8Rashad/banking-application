package com.example.bankapplication.dao.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.Date;

@Entity
@NoArgsConstructor
@Table(name = "users")
@Builder
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String fullName;
    private Date dateOfBirth;
    private String email;
    private String phone;

    @OneToOne
    @JoinColumn(name = "bank_account_id")
    private BankAccount bankAccountId;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User(Long id, String username, String password, String fullName, Date dateOfBirth, String email, String phone, BankAccount initialBalance) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phone = phone;
        this.bankAccountId = initialBalance;
    }

}
