package com.example.nanuer_server.domain.repository.Chat;

import com.example.nanuer_server.domain.entity.ChatRoomEntity;
import com.example.nanuer_server.domain.entity.PostEntity;
import com.example.nanuer_server.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, String> {
    Optional<ChatRoomEntity> findByRoomId(String roomId);
}