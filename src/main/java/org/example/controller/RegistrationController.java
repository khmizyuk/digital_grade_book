package org.example.controller;

import org.example.services.RegistrationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RegistrationController {
    @Autowired
    private RegistrationDAO registrationService;

    @PostMapping("/user/add")
    public @ResponseBody
    String registration(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String accountType, @RequestParam String login, @RequestParam String password) {
        return registrationService.registration(firstName, lastName, accountType, login, password);
    }

    @PostMapping("/user/add_by_token")
    public @ResponseBody
    String registration(@RequestParam String firstName, @RequestParam String lastName, @RequestParam String accountType, @RequestParam String token) {
        return registrationService.registration(firstName, lastName, accountType, token);
    }
}