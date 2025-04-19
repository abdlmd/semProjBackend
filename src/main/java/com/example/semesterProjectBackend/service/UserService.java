package com.example.semesterProjectBackend.service;

import ch.qos.logback.classic.html.DefaultThrowableRenderer;
import com.example.semesterProjectBackend.model.Users;
import com.example.semesterProjectBackend.repository.UserRepository;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository repo;

    public ResponseEntity<?> registerUser(Users users){
        String encodedPassword = bCryptPasswordEncoder.encode(users.getPassword());
        users.setPassword(encodedPassword);
        repo.save(users);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    public ResponseEntity<?> loginUser(Users user) {
        String email = user.getEmail();
        String password=user.getPassword();
        Optional<Users> dbUser = repo.findByEmail(email);
        if(dbUser.isEmpty())
            return new ResponseEntity<>("User not found",HttpStatus.BAD_REQUEST);
       String dbUserPassword = dbUser.get().getPassword();;
        if(bCryptPasswordEncoder.matches(password,dbUserPassword))
        {
            return new ResponseEntity<>("Succesfully Logged In",HttpStatus.OK);
        }
        else return new ResponseEntity<>("Wrong password",HttpStatus.UNAUTHORIZED);
    }
}
