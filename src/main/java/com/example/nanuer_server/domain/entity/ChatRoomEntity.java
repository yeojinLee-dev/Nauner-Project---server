package com.example.nanuer_server.domain.entity;


import lombok.*;
import org.apache.catalina.User;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(name="room")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@DynamicUpdate
@DynamicInsert
@Entity
@ToString(callSuper = true) // 부모 클래스의 toString 불러오는 어노테이션. 붙이면 createdAt 하고 updatedAt 데이터 정상적으로 나옴.
public class ChatRoomEntity implements Serializable {

    private static final long serialVersionUID = 6494678977089006639L;
    @Id
    @Column(nullable = false,unique = true)
    private String roomId;

    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    @ToString.Exclude
    private PostEntity postEntity;

    public static ChatRoomEntity create(){
        ChatRoomEntity room = new ChatRoomEntity();
        room.roomId = UUID.randomUUID().toString();
        return room;
    }


}
