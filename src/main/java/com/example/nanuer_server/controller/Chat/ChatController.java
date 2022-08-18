package com.example.nanuer_server.controller.Chat;

import com.example.nanuer_server.config.BaseException;
import com.example.nanuer_server.config.BaseResponse;
import com.example.nanuer_server.config.User.JwtTokenProvider;
import com.example.nanuer_server.domain.entity.ChatMessageEntity;
import com.example.nanuer_server.domain.entity.ChatRoomEntity;
import com.example.nanuer_server.domain.repository.UserRepository;
import com.example.nanuer_server.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final JwtTokenProvider jwtTokenProvider;
    private final ChatService chatService;
    private final UserRepository userRepository;

    private final SimpMessageSendingOperations simpMessageSendingOperations;

    /*
        /sub/channel/12345      - 구독(channelId:12345)
        /pub/hello              - 메시지 발행
    */

    @MessageMapping("/send") //채팅방에서 메세지 보내기 버튼
    public void message(ChatMessageEntity message, @RequestHeader("X-AUTH-TOKEN") String token) {
        // System.out.println("토큰 : " + token);
        String userEmail = jwtTokenProvider.getUserPk(token);
        String nickName = userRepository.findByEmail(userEmail).get().getNickName();
        //message.setSender(nickName);
        chatService.sendChatMessage(message);
        //simpMessageSendingOperations.convertAndSend("/sub/channel/" + message.getChannelId(), message);
    }

    @PostMapping("/chat/join")
    @ResponseBody
    public BaseResponse<String> createRoom(HttpServletRequest request, @RequestParam int postId) throws BaseException {
        ChatRoomEntity room = chatService.createRoom(request, postId);
        return new BaseResponse<>(room.getRoomId());
    }
}