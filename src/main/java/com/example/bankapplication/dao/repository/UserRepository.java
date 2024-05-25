package com.example.bankapplication.dao.repository;

import com.example.bankapplication.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
