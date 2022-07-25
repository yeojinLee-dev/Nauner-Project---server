package com.example.nanuer_server.domain.repository;

import com.example.nanuer_server.domain.entity.MyPageEntity;
import com.example.nanuer_server.domain.entity.PostEntity;
import com.example.nanuer_server.dto.post.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity,Long> {
    List<PostEntity> findAllByMyPageEntity(MyPageEntity myPageEntity);
}
