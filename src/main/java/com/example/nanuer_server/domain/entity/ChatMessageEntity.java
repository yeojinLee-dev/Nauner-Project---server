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
    private int sender;
    private int roomId;
    private String data;


    public void setSender(int sender) {this.sender = sender;}
    public void setData(String data){
        this.data=data;
    }


}
