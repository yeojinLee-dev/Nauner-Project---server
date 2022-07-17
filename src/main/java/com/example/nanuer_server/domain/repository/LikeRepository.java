package com.example.nanuer_server.domain.repository;

import com.example.nanuer_server.domain.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeEntity,Long> {
}
