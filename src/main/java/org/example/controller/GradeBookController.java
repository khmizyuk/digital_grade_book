package org.example.controller;

import org.example.services.GradeBookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class GradeBookController {
    @Autowired
    private GradeBookDAO gradeBookService;

    @GetMapping("/user/grades")
    public @ResponseBody
    String getTable(@RequestParam String login, @RequestParam String password) {
        return gradeBookService.getTable(login, password);
    }

    @PostMapping("/user/grades/add")
    public @ResponseBody
    String addGrade(@RequestParam String studentId,
                        @RequestParam String subject,
                        @RequestParam String mark,
                        @RequestParam String login,
                        @RequestParam String password) {
        return gradeBookService.addGrade(studentId, subject, mark, login, password);
    }

    @PutMapping("/user/grades/change")
    public @ResponseBody
    String changeGrade(@RequestParam Long gradeId,
                       @RequestParam String mark,
                       @RequestParam String login,
                       @RequestParam String password) {
        return gradeBookService.changeGrade(gradeId, mark, login, password);
    }

    @DeleteMapping("/user/grades/delete")
    public @ResponseBody
    String deleteGrade(@RequestParam Long gradeId,
                       @RequestParam String login,
                       @RequestParam String password) {
        return gradeBookService.deleteGrade(gradeId, login, password);
    }
}