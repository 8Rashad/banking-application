package com.example.bankapplication.model;


import com.example.bankapplication.dao.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankResponse {

    private Long id;
    private double balance;
    private User user;

}
