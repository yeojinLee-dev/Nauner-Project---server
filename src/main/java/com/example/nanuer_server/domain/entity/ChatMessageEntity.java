package com.example.nanuer_server.domain.entity;

import lombok.*;


import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Setter
public class ChatMessageEntity {
    public enum Type {
        ENTER, TALK, QUIT
    }
    private Type type;
    private String sender;
    private String channelId;
    private Object data;

    public void setSender(String sender) {this.sender = sender;}
    public void setData(String data){
        this.data=data;
    }


}
