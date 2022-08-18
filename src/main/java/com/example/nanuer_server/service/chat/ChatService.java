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
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
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

    // 채팅방(topic)에 발행되는 메시지를 처리할 Listner
    private final RedisMessageListenerContainer redisMessageListener;
    // 구독 처리 서비스
    private final RedisSubscriber redisSubscriber;

    private final RedisPublisher redisPublisher;
    // Redis
    private static final String CHAT_ROOMS = "CHAT_ROOM";
    public static final String ENTER_INFO = "ENTER_INFO"; // 채팅룸에 입장한 클라이언트의 sessionId와 채팅룸 id를 맵핑한 정보 저장
    private final RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, ChatRoomEntity> opsHashChatRoom;
    // 채팅방의 대화 메시지를 발행하기 위한 redis topic 정보. 서버별로 채팅방에 매치되는 topic정보를 Map에 넣어 roomId로 찾을수 있도록 한다.
    private Map<String, ChannelTopic> topics;
    private HashOperations<String, String, String> hashOpsEnterInfo;
    @PostConstruct
    private void init() {
        opsHashChatRoom = redisTemplate.opsForHash();
        hashOpsEnterInfo=redisTemplate.opsForHash();

        topics = new HashMap<>();
    }
    private final ChatRoomRepository chatRoomRepository;
    private Map<String, ChatRoomEntity> chatRooms;
    private final SimpMessageSendingOperations simpMessageSendingOperations;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final UserRepository userRepository;
    private final PostRepository postRepository;


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

    public ChatRoomEntity findRoomById(String id) {
        ChatRoomEntity chatRoom=(ChatRoomEntity)chatRoomRepository.findById(id).orElseThrow();
        return chatRoom;
    }
    public ChatRoomEntity createChatRoom(UserEntity customer, PostEntity store) {
        //String name=customer.getName()+"와 "+store.getName();
        ChatRoomEntity chatRoom = ChatRoomEntity.create(customer,store);
        opsHashChatRoom.put(CHAT_ROOMS, chatRoom.getRoomId(), chatRoom);
        chatRoomRepository.save(chatRoom);
        return chatRoom;
    }
    /**
     * 채팅방 입장 : redis에 topic을 만들고 pub/sub 통신을 하기 위해 리스너를 설정한다.
     */
    public void enterChatRoom(String roomId) {
        ChannelTopic topic = topics.get(roomId);
        if (topic == null)
            topic = new ChannelTopic(roomId);
        redisMessageListener.addMessageListener(redisSubscriber, topic);
        topics.put(roomId, topic);
    }
    public ChannelTopic getTopic(String roomId) {
        return topics.get(roomId);
    }
    //유저로 채팅방 찾기
    public List<ChatRoomEntity> getUserEnterRooms(UserEntity userEntity){
       return chatRoomRepository.findByRoomByUser(userEntity);
    }
    //게시물로 채팅방 찾기
    public List<ChatRoomEntity> getPostEnterRooms(PostEntity postEntity){
        return chatRoomRepository.findByRoomByPost(postEntity);
    }
    //채팅방 삭제
    public void deleteById(ChatRoomEntity chatRoom){
        chatRoomRepository.delete(chatRoom);
    }

    //destination정보에서 roomId 추출
    public String getRoomId(String destination) {
        int lastIndex = destination.lastIndexOf('/');
        if (lastIndex != -1)
            return destination.substring(lastIndex + 1);
        else
            return "";
    }

    // 유저가 입장한 채팅방ID와 유저 세션ID 맵핑 정보 저장
    public void setUserEnterInfo(String sessionId, String roomId) {
        hashOpsEnterInfo.put(ENTER_INFO, sessionId, roomId);
    }

    // 유저 세션으로 입장해 있는 채팅방 ID 조회
    public String getUserEnterRoomId(String sessionId) {
        return hashOpsEnterInfo.get(ENTER_INFO, sessionId);
    }

    // 유저 세션정보와 맵핑된 채팅방ID 삭제
    public void removeUserEnterInfo(String sessionId) {
        hashOpsEnterInfo.delete(ENTER_INFO, sessionId);
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
        redisPublisher.publish(getTopic(chatMessage.getChannelId()), chatMessage);

        //simpMessageSendingOperations.convertAndSend("/sub/channel/" + chatMessage.getChannelId(), chatMessage);


        //redisTemplate.convertAndSend(channelTopic.getTopic(), chatMessage);
    }



}
