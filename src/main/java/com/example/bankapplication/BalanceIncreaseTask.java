package com.example.bankapplication;

import com.example.bankapplication.dao.entity.BankAccount;
import com.example.bankapplication.dao.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@RequiredArgsConstructor
public class BalanceIncreaseTask {
    private final BankAccountRepository bankAccountRepository;



    @Scheduled(fixedRate = 60000)
    public void increaseBalances() {
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();

        for (BankAccount account : bankAccounts) {
            double initialBalance = account.getInitialBalance();
            double currentBalance = account.getBalance();

            double increasedBalance = currentBalance * 1.05;

            double maxBalance = initialBalance * 2.07;
            if (increasedBalance > maxBalance) {
                increasedBalance = maxBalance;
            }

            account.setBalance(increasedBalance);
            bankAccountRepository.save(account);
        }
    }
}
