package org.example.services;

import org.example.Entity.User;
import org.example.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthDAO {
    private final UserRepository userRepository;

    @Autowired
    public AuthDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String auth(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(password)) {
                return "You have signed in!";
            }
            else
                return "Error while signing in!";
        }
        return "Error while signing in!";
    }
}
