package org.example.controller;

import org.example.services.FindUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FindUserController {

    @Autowired
    private FindUserDAO userService;

    @GetMapping("/user/{id}")
    public @ResponseBody
    String findUserById(@PathVariable String id, @RequestParam String login, @RequestParam String password) {
        return userService.findUserById(id, login, password);
    }

    @GetMapping("/users")
    public @ResponseBody String findAllUsers(@RequestParam String login, @RequestParam String password) {
        return userService.findAllUsers(login, password);
    }

    @GetMapping("/users/students")
    public @ResponseBody String findAllStudents(@RequestParam String login, @RequestParam String password) {
        return userService.findAllStudents(login, password);
    }

    @GetMapping("/user")
    public @ResponseBody
    String showUserInfo(@RequestParam String login, @RequestParam String password) {
        return userService.showUserInfo(login, password);
    }
}