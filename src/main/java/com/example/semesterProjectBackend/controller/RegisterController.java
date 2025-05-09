package com.example.semesterProjectBackend.controller;

import com.example.semesterProjectBackend.model.Users;
import com.example.semesterProjectBackend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RegisterController {
    @Autowired
    private UserService service;
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Users users){
        return service.registerUser(users);

    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Users user, HttpServletRequest request){
        return service.loginUser(user,request);
    }
}
