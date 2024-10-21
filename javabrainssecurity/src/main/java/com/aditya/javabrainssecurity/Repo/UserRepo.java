package com.aditya.javabrainssecurity.Repo;

import com.aditya.javabrainssecurity.Models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    // Corrected method name
    boolean existsByUsername(String username);

    // Other methods
    Optional<UserEntity> findByUsername(String username);
}
