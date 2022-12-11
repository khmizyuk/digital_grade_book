package org.example.services;

import org.example.Entity.GradeBook;
import org.example.Entity.User;
import org.example.repo.GradeBookRepository;
import org.example.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.example.App.GSON;

@Service
public class GradeBookDAO {

    private final GradeBookRepository gradeBookRepository;
    private final UserRepository userRepository;

    @Autowired
    public GradeBookDAO(GradeBookRepository gradeBookRepository, UserRepository userRepository) {
        this.gradeBookRepository = gradeBookRepository;
        this.userRepository = userRepository;
    }

    public String getTable(String login, String password) {
        if (isStudent(login, password)) {
            Iterable<org.example.Entity.GradeBook> gradesOptional = gradeBookRepository.findAll();
            List<GradeBook> currentUserGrades = new ArrayList<>();;

            for (GradeBook grade: gradesOptional) {
                if (grade.getStudentLogin().equals(login)) {
                    currentUserGrades.add(grade);
                }
            }
            return GSON.toJson(currentUserGrades);
        }
        else if (isStaff(login, password)) {
            Iterable<org.example.Entity.GradeBook> gradesOptional = gradeBookRepository.findAll();
            List<GradeBook> currentUserGrades = new ArrayList<>();;

            for (GradeBook grade: gradesOptional) {
                if (grade.getTeacherLogin().equals(login)) {
                    currentUserGrades.add(grade);
                }
            }
            return GSON.toJson(currentUserGrades);
        }
        else
            return "Permission denied!";
    }

    public String addGrade(String studentLogin, String subject, String mark, String login, String password) {
        if (isStaff(login, password)) {
            GradeBook grade = new GradeBook();
            grade.setDate(new Date());
            grade.setStudentLogin(studentLogin);
            grade.setSubject(subject);
            grade.setMark(mark);
            grade.setTeacherLogin(login);
            gradeBookRepository.save(grade);
            return "Success!";
        }
        return "Permission denied!";
    }

    public String changeGrade(Long gradeId, String mark, String login, String password) {
        if (isStaff(login, password)) {

            Optional<GradeBook> gradeOptional = gradeBookRepository.findById(gradeId);

            if (gradeOptional.isPresent()) {
                GradeBook gradeBook = gradeOptional.get();
                gradeBook.setMark(mark);
                gradeBook.setDate(new Date());
                gradeBookRepository.save(gradeBook);
                return "Success!";
            }
            return "Record with ID " + gradeId + " not found.";
        }
        return "Permission denied!";
    }

    public String deleteGrade(Long gradeId, String login, String password) {
        if (isStaff(login, password)) {

            Optional<GradeBook> gradeOptional = gradeBookRepository.findById(gradeId);

            if (gradeOptional.isPresent()) {
                GradeBook gradeBook = gradeOptional.get();
                gradeBookRepository.delete(gradeBook);
                return "Success!";
            }
            return "Record with ID " + gradeId + " not found.";
        }
        return "Permission denied!";
    }

    public Boolean isStudent(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return (user.getPassword().equals(password) && user.getAccountType().equals("student"));
        }
        return false;
    }

    public Boolean isStaff(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return (user.getPassword().equals(password) && user.getAccountType().equals("staff"));
        }
        return false;
    }
}
