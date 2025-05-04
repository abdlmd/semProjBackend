package com.example.semesterProjectBackend.service;

import ch.qos.logback.classic.html.DefaultThrowableRenderer;
import com.example.semesterProjectBackend.model.Users;
import com.example.semesterProjectBackend.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository repo;
    @Autowired
    private AuthenticationManager authenticationManager;

    public ResponseEntity<?> registerUser(Users users){
        String encodedPassword = bCryptPasswordEncoder.encode(users.getPassword());
        users.setPassword(encodedPassword);
        repo.save(users);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }


    public ResponseEntity<?> loginUser(Users user, HttpServletRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            HttpSession session = request.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
            return new ResponseEntity<>("Succesfully Logged in",HttpStatus.OK);

        }catch(BadCredentialsException e) {
            return new ResponseEntity<>("Wrong credentials", HttpStatus.UNAUTHORIZED);
        }
    }
    public Optional<Users> getUserByEmail(String email){
        return repo.findByEmail(email);
    }

    public ResponseEntity<?> getPoster(int id) {
        Users users = repo.findById(id).orElseThrow();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    public ResponseEntity<?> getUser(Principal principal) {
        String email = principal.getName();
        Users users = repo.findByEmail(email).orElseThrow();
        return new ResponseEntity<>(users,HttpStatus.OK);

    }
}
