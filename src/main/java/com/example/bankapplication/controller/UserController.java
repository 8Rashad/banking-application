package com.example.bankapplication.controller;


import com.example.bankapplication.dao.entity.BankAccount;
import com.example.bankapplication.model.SearchRequest;
import com.example.bankapplication.dao.entity.User;
import com.example.bankapplication.model.UserRequest;
import com.example.bankapplication.service.UserManagementApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class UserController {

    private final UserManagementApi userManagementApi;

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){

        return userManagementApi.findAll();
    }

    @PostMapping("/post/users")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody UserRequest userRequest) {
        System.out.println(userRequest.toString());
        userManagementApi.saveUser(userRequest);

    }

    public List<User> searchUsers(@RequestBody SearchRequest searchRequest){
        return userManagementApi.searchUsers(searchRequest.minDateOfBirth, searchRequest.email, searchRequest.phone, searchRequest.fullName, searchRequest.pageNumber, searchRequest.pageSize, searchRequest.sortBy);
    }
}
