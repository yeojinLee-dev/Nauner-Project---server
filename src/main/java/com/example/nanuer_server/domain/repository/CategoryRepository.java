package com.example.nanuer_server.domain.repository;

import com.example.nanuer_server.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
