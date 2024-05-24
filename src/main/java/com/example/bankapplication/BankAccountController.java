package com.example.bankapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bank")
public class BankAccountController {
    private final BankService bankService;

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
