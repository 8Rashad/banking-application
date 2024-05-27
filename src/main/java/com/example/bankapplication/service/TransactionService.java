package com.example.bankapplication.service;

import com.example.bankapplication.dao.entity.BankAccount;
import com.example.bankapplication.dao.repository.BankAccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final BankAccountRepository bankAccountRepository;

    @Transactional
    public void transferMoney(Long senderAccountId, Long receiverAccountId, double amount) throws AccountNotFoundException, InsufficientBalanceException {
        BankAccount senderAccount = bankAccountRepository.findById(senderAccountId)
                .orElseThrow(() -> new AccountNotFoundException("Sender account not found"));

        BankAccount receiverAccount = bankAccountRepository.findById(receiverAccountId)
                .orElseThrow(() -> new AccountNotFoundException("Receiver account not found"));

        if (senderAccount.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient balance in sender account");
        }
        senderAccount.setBalance(senderAccount.getBalance()-amount);

        receiverAccount.setBalance(receiverAccount.getBalance() + amount);

        bankAccountRepository.save(senderAccount);
        bankAccountRepository.save(receiverAccount);
    }

    public class InsufficientBalanceException extends Throwable {
        public InsufficientBalanceException(String insufficientBalanceInSenderAccount) {
        }
    }
}

