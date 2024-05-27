package com.example.bankapplication.service;

import com.example.bankapplication.dao.entity.BankAccount;
import com.example.bankapplication.dao.repository.BankAccountRepository;
import com.example.bankapplication.mapper.BankMapper;
import com.example.bankapplication.model.BankRequest;
import com.example.bankapplication.model.BankResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankService {

    private final BankAccountRepository bankAccountRepository;

    public BankResponse saveBankAccount(BankRequest bankRequest){
        BankAccount save = bankAccountRepository.save(BankMapper.mapToBankAccount(bankRequest));
        return BankMapper.fromBankAccountToResponse(save);
    }

    public List<BankAccount> getAllBankAccounts(){
        return bankAccountRepository.findAll();
    }

    public void deposit(Long id, double amount) {
        BankAccount account = bankAccountRepository.getById(id);
        double currentBalance = account.getBalance();
        currentBalance += amount;
        account.setBalance(currentBalance);
        bankAccountRepository.save(account);
    }

    public boolean withdraw(Long id,double amount) {
        BankAccount account = bankAccountRepository.getById(id);
        double currentBalance = account.getBalance();
        if (currentBalance >= amount) {
            currentBalance -= amount;
            account.setBalance(currentBalance);
            bankAccountRepository.save(account);
            return true;
        } else {
            System.out.println("Not enough balance...");
            return false;
        }
    }

}
