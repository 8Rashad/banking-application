package com.example.bankapplication;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserManagementApi {
    private static Map<String, User> users = new HashMap<>();
    private static List<User> allUsers = new ArrayList<>();

    public static boolean addUser(String username, String password, String fullName, Date dateOfBirth, String email, String phone, BankAccount initialBalance) {
        if (isUsernameTaken(username) || isPhoneTaken(phone) || isEmailTaken(email)) {
            return false;
        }

        User newUser = new User(username, password, fullName, dateOfBirth, email, phone, initialBalance);
        users.put(username, newUser);
        return true;
    }

    public List<User> findAll(){
        return allUsers;
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


}
