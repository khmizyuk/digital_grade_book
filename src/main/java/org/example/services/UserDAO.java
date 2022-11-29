package org.example.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.CurrentUser;
import org.example.Entity.User;
import org.example.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDAO {
    private final UserRepository userRepository;

    @Autowired
    public UserDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String showUserInfo() {
        if (CurrentUser.accountType != null) {
            Optional<User> userOptional = userRepository.findById(CurrentUser.id);

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                return "Welcome back, " + user.getFirstName() + " " + user.getLastName() + "!";
            }
        }
        return "No users here... Sign in for continue.";
    }

    public String findUserById(String id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (CurrentUser.accountType != null && CurrentUser.accountType.equals("staff")) {
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                Gson gson = new GsonBuilder()
                        .setPrettyPrinting()
                        .create();
                return gson.toJson(user);
            }
            return "User with ID " + id + " not found.";
        }
        else {
            return "Permission denied!";
        }
    }

    public Iterable<org.example.Entity.User> findAllUsers() {
        Iterable<org.example.Entity.User> users = userRepository.findAll();

        if (CurrentUser.accountType != null && CurrentUser.accountType.equals("staff")) {
            return users;
        }
        else {
            return null;
        }
    }

    public List<User> findAllStudents() {
        Iterable<org.example.Entity.User> users = userRepository.findAll();
        List<User> students = new ArrayList<>();

        if (CurrentUser.accountType != null && CurrentUser.accountType.equals("staff")) {
            for (User user: users) {
                if (user.getAccountType().equals("student")) {
                    students.add(user);
                }
            }
        }
        else {
            return null;
        }
        return students;
    }

}
