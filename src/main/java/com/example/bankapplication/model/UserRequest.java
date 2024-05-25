package com.example.bankapplication.model;


import com.example.bankapplication.dao.entity.BankAccount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    private String username;
    private String password;
    private String fullName;
    private Date dateOfBirth;
    private String email;
    private String phone;
    private BankAccount initialBalance;

}
