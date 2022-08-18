package com.example.nanuer_server.config.Chat;


import com.example.nanuer_server.config.User.JwtTokenProvider;
import com.example.nanuer_server.domain.entity.ChatMessageEntity;
import com.example.nanuer_server.domain.repository.UserRepository;
import com.example.nanuer_server.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StompHandler implements ChannelInterceptor {
    private final JwtTokenProvider jwtTokenProvider;
    //private final ChatService chatService;
    private final UserRepository userRepository;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        System.out.println("message:" + message);
        System.out.println("헤더 : " + message.getHeaders());

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            jwtTokenProvider.validateToken(Objects.requireNonNull(accessor.getFirstNativeHeader("X-AUTH-TOKEN")));
            System.out.println("토큰 : " + accessor.getNativeHeader("X-AUTH-TOKEN"));
            //String token = Objects.requireNonNull(accessor.getFirstNativeHeader("X-AUTH-TOKEN"));
        }  else if (StompCommand.SUBSCRIBE == accessor.getCommand()) { // 채팅룸 구독요청
            // header정보에서 구독 destination정보를 얻고, roomId를 추출한다.
            //String roomId = chatService.getRoomId(Optional.ofNullable((String) message.getHeaders().get("simpDestination")).orElse("InvalidRoomId"));
            // 채팅방에 들어온 클라이언트 sessionId를 roomId와 맵핑해 놓는다.(나중에 특정 세션이 어떤 채팅방에 들어가 있는지 알기 위함)
            //String sessionId = (String) message.getHeaders().get("simpSessionId");
            //System.out.println("세션 아이디 : " + sessionId);
            //chatRoomRepository.setUserEnterInfo(sessionId, roomId);
            // 채팅방의 인원수를 +1한다.
            //chatRoomRepository.plusUserCount(roomId);
            // 클라이언트 입장 메시지를 채팅방에 발송한다.(redis publish)
            //String token = Objects.requireNonNull(accessor.getFirstNativeHeader("X-AUTH-TOKEN"));
            //String name = userRepository.findByEmail(jwtTokenProvider.getUserPk(token)).get().getNickName();
            //String name = Optional.ofNullable((Principal) message.getHeaders().get("simpUser")).map(Principal::getName).orElse("UnknownUser");
            //chatService.sendChatMessage(ChatMessageEntity.builder().type(ChatMessageEntity.Type.ENTER).channelId(roomId).sender(name).build());
            //System.out.println("구독 정보 : " + name + roomId);

        }
        return message;
    }
}
