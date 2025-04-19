package com.example.semesterProjectBackend.repository;

import com.example.semesterProjectBackend.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Integer> {
    Optional<Users> findByEmail (String Email);
}
