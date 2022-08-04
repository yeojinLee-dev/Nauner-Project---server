package com.example.nanuer_server.domain.repository;

import com.example.nanuer_server.domain.entity.HeartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HeartRepository extends JpaRepository<HeartEntity,Integer> {
    @Query("select l from HeartEntity l join fetch l.userEntity where l.userEntity.userId = :userId")
    List<HeartEntity> findAll(@Param("userId")int userId);
}
