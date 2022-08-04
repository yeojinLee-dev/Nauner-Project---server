package com.example.nanuer_server.domain.repository;

import com.example.nanuer_server.domain.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
    @Query(value = "select p from PostEntity p " +
            "join fetch p.userEntity where p.userEntity.userId = :user_id and p.postStatus = :post_status and (p.title like %:query% or p.content like %:query%)")
    List<PostEntity> findAll(@Param("user_id") int user_id, @Param("post_status") int status, @Param("query") String query);
}
