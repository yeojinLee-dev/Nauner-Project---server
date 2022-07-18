package com.example.nanuer_server.domain.repository;

import com.example.nanuer_server.domain.entity.UserEntity;
import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<UserEntity,Long> {
     Optional<UserEntity> findById(String id);

     List<UserEntity> findAll();
}
