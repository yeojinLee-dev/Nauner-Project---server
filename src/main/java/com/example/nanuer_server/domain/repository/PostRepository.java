package com.example.nanuer_server.domain.repository;

import com.example.nanuer_server.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    @Query(value = "select p from Post p " +
            "join fetch p.user where p.user.userId = :user_id and p.postStatus = :post_status and (p.title like %:query% or p.content like %:query%)")
    List<Post> findAll(@Param("user_id") int user_id, @Param("post_status") int status, @Param("query") String query);

    @Query(value = "select p.view from Post p where p.postId = :post_id")
    int findViewByPostId(@Param("post_id") int post_id);

    @Transactional
    @Modifying
    @Query(value = "update Post p set p.view = :view where p.postId = :post_id")
    int saveView(@Param("view") int view, @Param("post_id") int post_id);

    @Transactional
    @Modifying
    @Query(value = "update Post p set p.postStatus = 0 where p.postId = :post_id")
    void savePostStatus(@Param("post_id") int post_id);
}
