package com.example.bankapplication.controller;

import com.example.bankapplication.dao.entity.BankAccount;
import com.example.bankapplication.model.BankRequest;
import com.example.bankapplication.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bank")
public class BankAccountController {
    private final BankService bankService;

    @GetMapping
    public List<BankAccount> getAllBankAccounts(){
        return bankService.getAllBankAccounts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveBankAccount(@RequestBody BankRequest bankRequest){
        System.out.println(bankRequest.toString());
        bankService.saveBankAccount(bankRequest);
    }

    @Autowired
    public BankAccountController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping("/{id}/deposit")
    public void deposit(@PathVariable BankAccount id, @RequestParam double amount) {
        bankService.deposit(id, amount);
    }

    @PostMapping("/{id}/withdraw")
    public boolean withdraw(@PathVariable BankAccount id, @RequestParam double amount) {
        return bankService.withdraw(id, amount);
    }

}
