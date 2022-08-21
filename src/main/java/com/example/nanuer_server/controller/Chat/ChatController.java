package com.example.nanuer_server.controller.Chat;

import com.example.nanuer_server.config.BaseException;
import com.example.nanuer_server.config.BaseResponse;
import com.example.nanuer_server.config.User.JwtTokenProvider;
import com.example.nanuer_server.domain.entity.ChatMessageEntity;
import com.example.nanuer_server.domain.entity.ChatRoomEntity;
import com.example.nanuer_server.domain.entity.UserEntity;
import com.example.nanuer_server.domain.repository.Chat.ChatRoomRepository;
import com.example.nanuer_server.domain.repository.UserRepository;
import com.example.nanuer_server.dto.Chat.GetChatUserDto;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final UserService userService;
    private final ChatService chatService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;

    /*
        /sub/channel/12345      - 구독(channelId:12345)
        /pub/send        - 메시지 발행
    */

    //방번호(roomId), 유저Id(sender)
    @GetMapping("/getInfo")
    public BaseResponse<GetChatUserDto> GetChatUser(HttpServletRequest request, @RequestParam int post_id) throws BaseException {
        int userId = userService.GetHeaderAndGetUser(request);
        int roomNumber = chatRoomRepository.findAllByPostId(post_id).get(0).getRoomNumber();
        ChatRoomEntity room = chatService.createRoom(request, post_id);
        GetChatUserDto getChatUserDto = GetChatUserDto.builder()
                .userId(userId)
                .roomNumber(roomNumber)
                .build();
        return new BaseResponse<>(getChatUserDto);
    }

    @MessageMapping("/send") //채팅방에서 메세지 보내기 버튼
    public ChatMessageEntity message(ChatMessageEntity message) {
        //String userEmail = jwtTokenProvider.getUserPk(token);
        //String nickName = userRepository.findByEmail(userEmail).get().getNickName();
        System.out.println("ChatController : message() => " + message.getData());
        chatService.sendChatMessage(message);
        return message;
    }

    @PostMapping("/join") //채팅방에서 입장 버튼 (채팅하기 버튼) [게시물 작성자 말고 채팅 참여자만 보이는]
    @ResponseBody
    public BaseResponse<Integer> createRoom(HttpServletRequest request, @RequestParam int postId) throws BaseException {

        ChatRoomEntity room = chatService.createRoom(request, postId);
        return new BaseResponse<>(room.getRoomNumber());
    }
   

}