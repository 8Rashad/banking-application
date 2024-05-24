package com.example.bankapplication;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserManagementApi userManagementApi;

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){

        return userManagementApi.findAll();
    }

    @PostMapping("/post/users")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> addUser(@RequestBody String username, String password, String fullName, Date dateOfBirth, String email, String phone, BankAccount initialBalance) {
        userManagementApi.addUser(username, password, fullName, dateOfBirth, email, phone, initialBalance);
        return ResponseEntity.created(null).build();
    }

    public List<User> searchUsers(@RequestBody SearchRequest searchRequest){
        return userManagementApi.searchUsers(searchRequest.minDateOfBirth, searchRequest.email, searchRequest.phone, searchRequest.fullName, searchRequest.pageNumber, searchRequest.pageSize, searchRequest.sortBy);
    }
}
