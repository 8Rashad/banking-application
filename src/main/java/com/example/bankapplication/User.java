package com.example.bankapplication;

import jakarta.persistence.Id;
import jakarta.persistence.Table;


import java.util.Date;


@Table(name = "users")
public class User {

    @Id
    private Long id;
    private String username;
    private String password;
    private String fullName;
    private Date dateOfBirth;
    private String email;
    private String phone;
    private BankAccount bankAccount;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public User(String username, String password, String fullName, Date dateOfBirth, String email, String phone, BankAccount bankAccount) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phone = phone;
        this.bankAccount = bankAccount;
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
