package com.example.bankapplication;

import com.example.bankapplication.dao.entity.BankAccount;
import com.example.bankapplication.model.BankRequest;
import com.example.bankapplication.model.UserRequest;
import com.example.bankapplication.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@RequiredArgsConstructor
public class BalanceIncreaseTask {
    private final BankService bankService;
    private final UserRequest userRequest;



    @Scheduled(fixedRate = 60000)
    public void increaseBalances() {
        // Получаем всех клиентов
        List<BankAccount> bankAccounts = bankService.getAllBankAccounts();

        // Перебираем каждого клиента
        for (BankAccount account : bankAccounts) {
            double initialBalance = account.getUser(userRequest.getInitialBalance());
            double currentBalance = account.getBalance();

            // Рассчитываем новый баланс с учетом увеличения на 5%
            double increasedBalance = currentBalance * 1.05;

            // Ограничиваем увеличение баланса до 207% от начального депозита
            double maxBalance = initialBalance * 2.07;
            if (increasedBalance > maxBalance) {
                increasedBalance = maxBalance;
            }

            // Обновляем баланс клиента
            account.setBalance(increasedBalance);
            bankService.saveBankAccount(account);
        }
    }
}
