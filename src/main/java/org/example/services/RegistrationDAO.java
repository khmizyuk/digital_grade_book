package org.example.services;

import org.example.Entity.User;
import org.example.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class RegistrationDAO {
    private final UserRepository userRepository;

    @Autowired
    public RegistrationDAO(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private String generatePassword() {
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                char letter = (char) (random.nextInt(25) + 97);
                password.append(letter);
            }
            if (i != 2)
                password.append("-");
        }
        return password.toString();

    }

    public String registration(String firstName, String lastName, String accountType, String token) {
        if (isAdmin(token)) {
            User user = User.newBuilder()
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setAccountType(accountType)
                    .setEmail(Character.toLowerCase(firstName.charAt(0)) + "." + lastName.toLowerCase() + "@42.fr")
                    .setPassword(generatePassword())
                    .build();
            userRepository.save(user);
            return "Account has created!";
        }
        return "Permission denied!";
    }

    public String registration(String firstName, String lastName, String accountType, String login, String password) {
        if (isStaff(login, password)) {
            User user = User.newBuilder()
                    .setFirstName(firstName)
                    .setLastName(lastName)
                    .setAccountType(accountType)
                    .setEmail(Character.toLowerCase(firstName.charAt(0)) + "." + lastName.toLowerCase() + "@42.fr")
                    .setPassword(generatePassword())
                    .build();
            userRepository.save(user);
            return "Account has created!";
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

    private Boolean isAdmin(String token) {
        return token.equals("gbqugr-sldjpy-atgoth-aghkvr-qogjlp-qlvgbe");
    }
}
