package com.example.bankapplication.dao.repository;

import com.example.bankapplication.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT PHONE FROM USERS", nativeQuery = true)
    List<String> getAllPhones();

    @Query(value = "SELECT EMAIL FROM USERS", nativeQuery = true)
    List<String> getAllEmails();

}

