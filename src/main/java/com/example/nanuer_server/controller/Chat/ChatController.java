package com.example.nanuer_server.controller.Chat;

import com.example.nanuer_server.domain.entity.ChatMessageEntity;
import com.example.nanuer_server.domain.entity.ChatRoomEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessageSendingOperations simpMessageSendingOperations;

    /*
        /sub/channel/12345      - 구독(channelId:12345)
        /pub/hello              - 메시지 발행
    */

    @MessageMapping("/send")
    public void message(ChatRoomEntity chatRoomEntity, ChatMessageEntity message) {


        simpMessageSendingOperations.convertAndSend("/sub/channel/" + message.getChannelId(), message);
    }
}