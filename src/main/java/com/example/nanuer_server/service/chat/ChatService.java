package com.example.nanuer_server.service.chat;

import com.example.nanuer_server.config.BaseException;
import com.example.nanuer_server.domain.entity.ChatMessageEntity;
import com.example.nanuer_server.domain.entity.ChatRoomEntity;
import com.example.nanuer_server.domain.entity.PostEntity;
import com.example.nanuer_server.domain.entity.UserEntity;
import com.example.nanuer_server.domain.repository.Chat.ChatRoomRepository;
import com.example.nanuer_server.domain.repository.PostRepository;
import com.example.nanuer_server.domain.repository.UserRepository;
import com.example.nanuer_server.dto.Post.PostChatDto;
import com.example.nanuer_server.dto.Post.PostDto;
import com.example.nanuer_server.service.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatService {

    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final UserService userService;
    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomEntity createRoom(HttpServletRequest request, int postId) throws BaseException {
        int userId = userService.GetHeaderAndGetUser(request);
        UserEntity userEntity = userRepository.findByUserId(userId).get();
        PostEntity postEntity = postRepository.findByPostId(postId);
        ChatRoomEntity chatRoomEntity = ChatRoomEntity.create(false,userEntity, postEntity);
        chatRoomRepository.save(chatRoomEntity);
        return chatRoomEntity;
    }

    public void sendChatMessage(ChatMessageEntity chatMessage) {
        simpMessageSendingOperations.convertAndSend("/sub/channel/" + chatMessage.getRoomId(), chatMessage);
    }
}
