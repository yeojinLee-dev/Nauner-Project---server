package com.example.nanuer_server.domain.repository;

import com.example.nanuer_server.domain.entity.PostEntity;
import com.example.nanuer_server.dto.Post.PostGetResDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity,Long> {
    @Query(value = "select p from post p join fetch p.category, p.user where p.user_id = :user_id", nativeQuery = true)
    List<PostEntity> findAllByUserId(@Param("user_id") int user_id);
}
