package com.example.nanuer_server.domain.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ChatMessageEntity {

    private String type;
    private String sender;
    private String channelId;
    private Object data;

    //private String chatRoomId;
    //private String writer;
    //private String message;
    //private MessageType type;

    public void setSender(String sender) {this.sender = sender;}

    public void newConnect(){
        this.type = "new";
    }
    public void closeConnect(){
        this.type = "close";
    }


}
