package com.example.nanuer_server.controller.Chat;

import com.example.nanuer_server.config.BaseException;
import com.example.nanuer_server.config.BaseResponse;
import com.example.nanuer_server.config.User.JwtTokenProvider;
import com.example.nanuer_server.domain.entity.ChatMessageEntity;
import com.example.nanuer_server.domain.entity.ChatRoomEntity;
import com.example.nanuer_server.domain.entity.UserEntity;
import com.example.nanuer_server.domain.repository.UserRepository;
import com.example.nanuer_server.service.User.UserService;
import com.example.nanuer_server.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.WebSocketHttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ChatMessageController {

    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final UserService userService;
    private final ChatService chatService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    /*
        /sub/channel/12345      - 구독(channelId:12345)
        /pub/send        - 메시지 발행
    */
    /*
    @MessageMapping("/join") //채팅방에서 입장 버튼 (채팅하기 버튼)
    public String JoinChat() {
        ChatRoomEntity room =  ChatRoomEntity.create();
        //simpMessageSendingOperations.convertAndSend("/sub/channel/" + room.getRoomId() , "방에 입장하셨습니다");
    }*/

    @MessageMapping("/send") //채팅방에서 메세지 보내기 버튼
    public void message(ChatMessageEntity message, @RequestHeader("X-AUTH-TOKEN") String token) {
       // System.out.println("토큰 : " + token);
        String userEmail = jwtTokenProvider.getUserPk(token);
        String nickName = userRepository.findByEmail(userEmail).get().getNickName();
        ChatRoomEntity chatRoom=chatService.findRoomById(message.getChannelId());
        //message.setSender(nickName);
        chatService.sendChatMessage(message);
        //simpMessageSendingOperations.convertAndSend("/sub/channel/" + message.getChannelId(), message);
    }
    /*
    @MessageMapping("/join")
    public void enter(HttpServletRequest request,ChatMessageEntity message) throws BaseException{
        message.setType(ChatMessageEntity.Type.ENTER);
        int userId = userService.GetHeaderAndGetUser(request);
        String nickName = userRepository.findByUserId(userId).get().getNickName();
        String roomId = chatService.createRoom();
        message.setChannelId(roomId);
        message.setSender(nickName);
        message.setData(message.getChannelId()+ "방에 " +message.getSender()+" 님이 입장하였습니다.");
        simpMessageSendingOperations.convertAndSend("/sub/channel/"+message.getChannelId(),message);
    }*/

    @PostMapping("/chat/join")
    @ResponseBody
    public BaseResponse<String> createRoom(HttpServletRequest request, @RequestParam int postId) throws BaseException {
        ChatRoomEntity room = chatService.createRoom(request, postId);
        return new BaseResponse<>(room.getRoomId());
    }

}