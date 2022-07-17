package com.example.nanuer_server.domain.repository;

import com.example.nanuer_server.domain.entity.MyPageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyPageRepository extends JpaRepository<MyPageEntity,Long> {
}
