package org.example.controller;

import org.example.services.AuthDAO;
import org.example.services.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Auth {
    @Autowired
    private UserDAO userService;

    @Autowired
    private AuthDAO authService;

    @GetMapping("/auth")
    public @ResponseBody
    String auth(@RequestParam String login, @RequestParam String password) {
        return authService.auth(login, password);
    }

    @GetMapping("/user")
    public @ResponseBody
    String showUserInfo() {
        return userService.showUserInfo();
    }
}

// http://localhost:8080/auth?login=khmizyuk.s.i@42.fr&password=cpdgus-tkfhoa-nlcbdr