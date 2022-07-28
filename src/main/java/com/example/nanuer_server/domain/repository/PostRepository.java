package com.example.nanuer_server.domain.repository;

import com.example.nanuer_server.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PostRepository extends JpaRepository<Post,Long> {
    @Query(value = "select p from Post p " +
            "join fetch p.user where p.user.userId = :user_id")
    List<Post> findAll(@Param("user_id") int user_id);
}
