package com.example.nanuer_server.domain.repository;

import com.example.nanuer_server.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface  UserRepository extends JpaRepository<UserEntity,Integer> {
     Optional<UserEntity> findByEmail(String email);

     List<UserEntity> findAll();
}