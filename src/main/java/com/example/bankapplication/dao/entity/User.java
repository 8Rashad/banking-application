package com.example.bankapplication.dao.entity;


import com.example.bankapplication.service.UserManagementApi;
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
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String fullName;
    private Date dateOfBirth;
    private String email;
    private String phone;

    @OneToOne
    @JoinColumn(name = "bank_account_id")
    private BankAccount initialBalance;



    public User(Long id, String username, String password, String fullName, Date dateOfBirth, String email, String phone, BankAccount intialBalance) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phone = phone;
        this.initialBalance = intialBalance;
    }

    public boolean changePhone(String newPhone) {
        if (!UserManagementApi.isPhoneTaken(newPhone)) {
            this.phone = newPhone;
            return true;
        } else {
            System.out.println("Unfortunately the number is already in use");
            return false;
        }
    }

    public boolean changeEmail(String newEmail) {
        if (!UserManagementApi.isEmailTaken(newEmail)) {
            this.email = newEmail;
            return true;
        } else {
            System.out.println("Unfortunately the email address is already in use");
            return false;
        }
    }

    public boolean deletePhone() {
        if (phone != null && email != null) {
            phone = null;
            return true;
        } else {
            System.out.println("The phone number cannot be deleted because the user must have at least one contact");
            return false;
        }
    }

    public boolean deleteEmail() {
        if (phone != null && email != null) {
            email = null;
            return true;
        } else {
            System.out.println("The email cannot be deleted because the user must have at least one contact");
            return false;
        }
    }





}
