package org.example.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.DTO.UserDTO;
import org.example.Entity.User;
import org.example.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.example.App.GSON;

@Service
public class FindUserDAO {

    private final UserRepository userRepository;

    @Autowired
    public FindUserDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String showUserInfo(String login, String password) {
        if (isStaff(login, password) || isStudent(login, password)) {
            Optional<User> userOptional = userRepository.findByEmail(login);

            if (userOptional.isPresent()) {
                User user = userOptional.get();
                UserDTO userDTO = new UserDTO(user.getEmail(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getAccountType());
                return GSON.toJson(userDTO);
            }
        }
        return "Permission denied!";
    }

    public String findUserById(String email, String login, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (isStaff(login, password)) {
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                UserDTO userDTO = new UserDTO(user.getEmail(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getAccountType());
                return GSON.toJson(userDTO);
            }
            return "User with login " + email + " not found.";
        }
        return "Permission denied!";
    }

    public String findAllUsers(String login, String password) {
        Iterable<org.example.Entity.User> users = userRepository.findAll();
        List<UserDTO> students = new ArrayList<>();

        if (isStaff(login, password)) {
            for (User user: users) {
                UserDTO userDTO = new UserDTO(user.getEmail(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getAccountType());
                students.add(userDTO);
            }
            return GSON.toJson(students);
        }
        return "Permission denied!";
    }

    public String findAllStudents(String login, String password) {
        Iterable<org.example.Entity.User> users = userRepository.findAll();
        List<UserDTO> students = new ArrayList<>();

        if (isStaff(login, password)) {
            for (User user: users) {
                if (user.getAccountType().equals("student")) {
                    UserDTO userDTO = new UserDTO(user.getEmail(),
                            user.getFirstName(),
                            user.getLastName(),
                            user.getAccountType());
                    students.add(userDTO);
                }
            }
            return GSON.toJson(students);
        }
        return "Permission denied!";
    }

    public Boolean isStaff(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return (user.getPassword().equals(password) && user.getAccountType().equals("staff"));
        }
        return false;
    }

    public Boolean isStudent(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return (user.getPassword().equals(password) && user.getAccountType().equals("student"));
        }
        return false;
    }
}
