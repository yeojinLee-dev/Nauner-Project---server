package com.example.nanuer_server.domain.repository;

import com.example.nanuer_server.domain.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity,Integer> {

    @Query("select p from PostEntity p join fetch p.category, p.userEntity where p.userEntity.userId = :user_id")
    List<PostEntity> findAllByUserId(@Param("user_id") int user_id);

}
