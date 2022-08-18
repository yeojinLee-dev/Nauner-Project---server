package com.example.nanuer_server.service.chat;


import com.example.nanuer_server.config.BaseException;
import com.example.nanuer_server.config.User.JwtTokenProvider;
import com.example.nanuer_server.domain.entity.ChatMessageEntity;
import com.example.nanuer_server.domain.entity.ChatRoomEntity;
import com.example.nanuer_server.domain.entity.PostEntity;
import com.example.nanuer_server.domain.entity.UserEntity;
import com.example.nanuer_server.domain.repository.Chat.ChatRoomRepository;
import com.example.nanuer_server.domain.repository.PostRepository;
import com.example.nanuer_server.domain.repository.UserRepository;
import com.example.nanuer_server.service.User.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class ChatService {

    private Map<String, ChatRoomEntity> chatRooms;
    private final ChatRoomRepository chatRoomRepository;
    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @PostConstruct
    //의존관게 주입완료되면 실행되는 코드
    private void init() {
        chatRooms = new LinkedHashMap<>();
    }


    //채팅방 생성 [1:1 채팅] (거래완료 전)
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

    public String getRoomId(String destination) {
        int lastIndex = destination.lastIndexOf('/');
        if (lastIndex != -1)
            return destination.substring(lastIndex + 1);
        else
            return "";
    }


     //채팅방에 메시지 발송

    public void sendChatMessage(ChatMessageEntity chatMessage) {
        //chatMessage.setUserCount(chatRoomRepository.getUserCount(chatMessage.getRoomId()));
        if (ChatMessageEntity.Type.ENTER.equals(chatMessage.getType())) {
            chatMessage.setData(chatMessage.getSender() + "님이 방에 입장했습니다.");
            chatMessage.setSender("[알림]");

        } else if (ChatMessageEntity.Type.QUIT.equals(chatMessage.getType())) {
            chatMessage.setData(chatMessage.getSender() + "님이 방에서 나갔습니다.");
            chatMessage.setSender("[알림]");
        }

        simpMessageSendingOperations.convertAndSend("/sub/channel/" + chatMessage.getChannelId(), chatMessage);


        //redisTemplate.convertAndSend(channelTopic.getTopic(), chatMessage);
    }



}
