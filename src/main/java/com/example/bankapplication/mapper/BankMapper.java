package com.example.bankapplication.mapper;

import com.example.bankapplication.dao.entity.BankAccount;
import com.example.bankapplication.model.BankRequest;
import com.example.bankapplication.model.BankResponse;

public class BankMapper {

    public static BankAccount mapToBankAccount(BankRequest bankRequest){
        return BankAccount.builder()
                .balance(bankRequest.getBalance())
                .user(bankRequest.getUser())
                .build();
    }

    public static BankResponse fromBankAccountToResponse(BankAccount bankAccount){
        return BankResponse.builder()
                .id(bankAccount.getId())
                .balance(bankAccount.getBalance())
                .user(bankAccount.getUser())
                .build();
    }

}
