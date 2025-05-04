package com.example.semesterProjectBackend.controller;

import com.example.semesterProjectBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@CrossOrigin
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService service;
    @GetMapping("/poster/{id}")
    public ResponseEntity<?> getPoster(@PathVariable int id){
        return service.getPoster(id);

    }
    @GetMapping("/user")
    public ResponseEntity<?> getUser(Principal principal){
        return service.getUser(principal);
    }
}
