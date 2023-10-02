package com.example.springsecurityaccio.controller;

import com.example.springsecurityaccio.dao.StudentRepo;
import com.example.springsecurityaccio.modal.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    StudentRepo studentRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to ADMIN area !!!";
    }

    @PostMapping("/add/username/{username}/password/{password}")
    public Student addStudent(@PathVariable("username") String username, @PathVariable("password") String password) {
        Student student = new Student();
        student.setUsername(username);
        student.setPassword(bCryptPasswordEncoder.encode(password));
        student.setRole("ROLE_ADMIN");
        return studentRepo.save(student);
    }
}
