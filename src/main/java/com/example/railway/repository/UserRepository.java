package com.example.railway.repository;

import com.example.railway.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // A method to find a user by their unique username
    Optional<User> findByUsername(String username);
}