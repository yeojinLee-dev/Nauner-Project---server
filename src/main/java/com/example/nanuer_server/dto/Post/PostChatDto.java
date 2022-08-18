package com.example.nanuer_server.dto.Post;

import com.example.nanuer_server.domain.entity.HeartEntity;
import com.example.nanuer_server.domain.entity.PostEntity;
import com.example.nanuer_server.domain.entity.UserEntity;
import com.example.nanuer_server.dto.User.UserInfoDto;

public class PostChatDto {
    private Integer postId;

    private UserEntity userEntity;

    public HeartEntity toEntity(){
        HeartEntity heartEntity = HeartEntity.builder()
                .userEntity(userEntity)
                .build();
        return heartEntity;
    }
}
