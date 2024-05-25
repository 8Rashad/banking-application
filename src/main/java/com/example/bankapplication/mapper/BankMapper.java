package com.example.bankapplication.mapper;

import com.example.bankapplication.dao.entity.BankAccount;
import com.example.bankapplication.model.BankRequest;

public class BankMapper {

    public static BankAccount mapToBankAccount(BankRequest bankRequest){
        return BankAccount.builder()
                .balance(bankRequest.getBalance())
                .user(bankRequest.getUser())
                .build();
    }

}
