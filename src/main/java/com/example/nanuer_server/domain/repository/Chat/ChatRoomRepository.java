package com.example.nanuer_server.domain.repository.Chat;

import com.example.nanuer_server.domain.entity.ChatRoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity, Integer> {

    @Query("select h from ChatRoomEntity h join fetch h.postEntity where h.postEntity.postId = :postId")
    Optional<ChatRoomEntity> findByPostId(@Param("postId") int postId);

    @Query("select h from ChatRoomEntity h join fetch h.postEntity where h.postEntity.postId = :postId")
    List<ChatRoomEntity> findAllByPostId(@Param("postId") int postId);

    /*
    @Query("select user_id from room where (is_writer=1) and (room_number=?)")
    List<ChatRoomEntity> findIsWriterByPostId(@Param("postId") int postId);
*/
}