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
        ChatRoomEntity chatRoom = ChatRoomEntity.builder()
                .roomId(UUID.randomUUID().toString())
                .postEntity(postEntity)
                .userEntity(userEntity)
                .build();
        chatRoomRepository.save(chatRoom);
        return chatRoom;
    }

    public void sendChatMessage(ChatMessageEntity chatMessage) {

        if (ChatMessageEntity.Type.ENTER.equals(chatMessage.getType())) {
            chatMessage.setData(chatMessage.getSender() + "님이 방에 입장했습니다.");
            chatMessage.setSender("[알림]");

        } else if (ChatMessageEntity.Type.QUIT.equals(chatMessage.getType())) {
            chatMessage.setData(chatMessage.getSender() + "님이 방에서 나갔습니다.");
            chatMessage.setSender("[알림]");
        }
        simpMessageSendingOperations.convertAndSend("/sub/channel/" + chatMessage.getChannelId(), chatMessage);
    }
}
