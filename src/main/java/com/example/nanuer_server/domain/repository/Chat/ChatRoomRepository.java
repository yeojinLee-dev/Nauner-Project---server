package com.example.nanuer_server.domain.repository.Chat;

import com.example.nanuer_server.domain.entity.ChatRoomEntity;
import com.example.nanuer_server.domain.entity.PostEntity;
import com.example.nanuer_server.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Integer> {

    @Query("select h from ChatRoomEntity h join fetch h.postEntity where h.postEntity.postId = :postId")
    Optional<ChatRoomEntity> findByPostId(@Param("postId") int postId);

}