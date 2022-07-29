package com.example.nanuer_server.domain.repository;

import com.example.nanuer_server.domain.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikeRepository extends JpaRepository<LikeEntity,Integer> {

    @Query("select l from LikeEntity l join fetch l.userEntity where l.userEntity.userId = :userId")
    List<LikeEntity> findAllByUserId(@Param("userId")Integer userId);
}
