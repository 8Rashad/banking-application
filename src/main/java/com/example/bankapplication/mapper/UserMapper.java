package com.example.bankapplication.mapper;

import com.example.bankapplication.dao.entity.BankAccount;
import com.example.bankapplication.dao.entity.User;
import com.example.bankapplication.model.UserRequest;

public class UserMapper {
    public static User buildUser(UserRequest userRequest){
        return User.builder()
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .fullName(userRequest.getFullName())
                .dateOfBirth(userRequest.getDateOfBirth())
                .email(userRequest.getEmail())
                .phone(userRequest.getPhone())
                .initialBalance(userRequest.getInitialBalance())
                .build();

    }
}
