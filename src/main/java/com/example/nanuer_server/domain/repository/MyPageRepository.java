package com.example.nanuer_server.domain.repository;

import com.example.nanuer_server.domain.entity.MyPageEntity;
import com.example.nanuer_server.dto.post.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyPageRepository extends JpaRepository<MyPageEntity,Long> {
    List<PostDto> findById(int id);
}
