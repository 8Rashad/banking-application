package com.example.bankapplication.service;

import com.example.bankapplication.dao.entity.BankAccount;
import com.example.bankapplication.dao.repository.BankAccountRepository;
import com.example.bankapplication.mapper.BankMapper;
import com.example.bankapplication.model.BankRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {
    private BankAccountRepository bankAccountRepository;

    public void saveBankAccount(BankRequest bankRequest){
        bankAccountRepository.save(BankMapper.mapToBankAccount(bankRequest));
    }

    public List<BankAccount> getAllBankAccounts(){
        return bankAccountRepository.findAll();
    }

    public void deposit(BankAccount account, double amount) {
        double currentBalance = account.getBalance();
        currentBalance += amount;
        account.setBalance(currentBalance);
    }

    public boolean withdraw(BankAccount account,double amount) {
        double currentBalance = account.getBalance();
        if (currentBalance >= amount) {
            currentBalance -= amount;
            account.setBalance(currentBalance);
            return true;
        } else {
            System.out.println("Not enough balance...");
            return false;
        }
    }
}
