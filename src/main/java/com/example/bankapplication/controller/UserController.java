package com.example.bankapplication.controller;


import com.example.bankapplication.model.SearchRequest;
import com.example.bankapplication.dao.entity.User;
import com.example.bankapplication.model.UserRequest;
import com.example.bankapplication.service.TransactionService;
import com.example.bankapplication.service.UserManagementApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserManagementApi userManagementApi;
    private final TransactionService transactionService;


    @GetMapping
    public List<User> retrieveAllUsers() {

        return userManagementApi.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody UserRequest userRequest) {
        System.out.println(userRequest.toString());
        userManagementApi.saveUser(userRequest);
    }

    @GetMapping("/search")
    public List<User> searchUsers(@RequestBody SearchRequest searchRequest) {
        return UserManagementApi.searchUsers(searchRequest.minDateOfBirth, searchRequest.email, searchRequest.phone, searchRequest.fullName, searchRequest.pageNumber, searchRequest.pageSize, searchRequest.sortBy);
    }

    @PutMapping("/{id}/changePhone")
    public boolean changePhone(@PathVariable Long id, @RequestParam String newPhone) {
        return userManagementApi.changePhone(id, newPhone);
    }

    @PutMapping("/{id}/changeEmail")
    public boolean changeEmail(@PathVariable Long id, @RequestParam String newEmail) {
        return userManagementApi.changeEmail(id, newEmail);
    }

    @PutMapping("/{id}/sendMoneyTo/{otherId}")
    public void sendMoneyTo(@PathVariable Long id, @PathVariable Long otherId, @RequestParam double amount) {
        try {
            transactionService.transferMoney(id, otherId, amount);
        } catch (AccountNotFoundException | TransactionService.InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }
    }
}
