package com.example.nanuer_server.domain.repository;

import com.example.nanuer_server.domain.entity.MyPageEntity;
import com.example.nanuer_server.dto.Post.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyPageRepository extends JpaRepository<MyPageEntity,Long> {
}
