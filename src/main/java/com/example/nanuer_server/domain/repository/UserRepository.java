package com.example.nanuer_server.domain.repository;

import com.example.nanuer_server.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface  UserRepository extends JpaRepository<User,Long> {
     Optional<User> findByEmail(String email);

     List<User> findAll();
}