package com.example.projectsapp.projectsapp.repository;

import com.example.projectsapp.projectsapp.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAll(Pageable pageable);
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByName(String name);
}
