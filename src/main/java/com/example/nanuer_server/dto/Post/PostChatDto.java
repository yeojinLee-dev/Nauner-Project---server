package com.example.nanuer_server.dto.Post;

import com.example.nanuer_server.domain.entity.HeartEntity;
import com.example.nanuer_server.domain.entity.PostEntity;
import com.example.nanuer_server.domain.entity.UserEntity;
import com.example.nanuer_server.dto.User.UserInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostChatDto {
    private int postId;
    private int userId;

    private UserEntity userEntity;

    public PostEntity toEntity(){
        PostEntity postEntity = PostEntity.builder()
                .postId(postId)
                .userEntity(userEntity)
                .build();
        return postEntity;
    }

}
