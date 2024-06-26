package com.example.bankapplication.service;

import com.example.bankapplication.dao.entity.BankAccount;
import com.example.bankapplication.dao.entity.User;
import com.example.bankapplication.dao.repository.BankAccountRepository;
import com.example.bankapplication.dao.repository.UserRepository;
import com.example.bankapplication.mapper.UserMapper;
import com.example.bankapplication.model.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor

public class UserManagementApi {
    private final UserRepository userRepository;
    private final BankAccountRepository bankAccountRepository;
    private static Map<String, User> users = new HashMap<>();

    public boolean saveUser(UserRequest userRequest) {
        if (isUsernameTaken(userRequest.getUsername()) || isPhoneTaken(userRequest.getPhone()) || isEmailTaken(userRequest.getEmail())) {
            return false;
        }

        User newUser = UserMapper.buildUser(userRequest);
        BankAccount initialBalance = userRequest.getInitialBalance();
        bankAccountRepository.save(initialBalance);


        userRepository.save(newUser);

        userRepository.flush();

        users.put(newUser.getUsername(), newUser);

        return true;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }


    private static boolean isUsernameTaken(String username) {

        return users.containsKey(username);
    }

    public static boolean isPhoneTaken(String phone) {
        for (User user : users.values()) {
            if (phone.equals(user.getPhone())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isEmailTaken(String email) {
        for (User user : users.values()) {
            if (email.equals(user.getEmail())) {
                return true;
            }
        }
        return false;
    }

    public static List<User> searchUsers(Date minDateOfBirth, String phone, String fullName, String email, int pageNumber, int pageSize, String sortBy) {
        List<User> result = new ArrayList<>();

        for (User user : users.values()) {
            if ((minDateOfBirth == null || user.getDateOfBirth().after(minDateOfBirth))
                    && (phone == null || user.getPhone().equals(phone))
                    && (fullName == null || user.getFullName().startsWith(fullName))
                    && (email == null || user.getEmail().equals(email))) {
                result.add(user);
            }
        }

        if (sortBy != null) {
            result.sort((u1, u2) -> {
                switch (sortBy) {
                    case "fullName":
                        return u1.getFullName().compareTo(u2.getFullName());
                    case "dateOfBirth":
                        return u1.getDateOfBirth().compareTo(u2.getDateOfBirth());
                    case "email":
                        return u1.getEmail().compareTo(u2.getEmail());
                    case "phone":
                        return u1.getPhone().compareTo(u2.getPhone());
                    default:
                        return 0;
                }
            });
        }

        int fromIndex = (pageNumber - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, result.size());
        return result.subList(fromIndex, toIndex);
    }

    public boolean changePhone(Long id, String newPhone) {
        User theUserId = userRepository.getById(id);
        List<String> allPhones = userRepository.getAllPhones();
        if(allPhones.contains(newPhone)){
            return false;
        }
        theUserId.setPhone(newPhone);
        userRepository.save(theUserId);
        return true;
    }

    public boolean changeEmail(Long id, String newEmail) {
        User theUserId = userRepository.getById(id);
        List<String> allEmails = userRepository.getAllEmails();
        if(allEmails.contains(newEmail)){
            return false;
        }
        theUserId.setEmail(newEmail);
        userRepository.save(theUserId);
        return true;
    }

}
