package com.example.nanuer_server.dto.heart;

import com.example.nanuer_server.domain.entity.HeartEntity;
import com.example.nanuer_server.domain.entity.PostEntity;
import com.example.nanuer_server.domain.entity.UserEntity;
import com.example.nanuer_server.dto.Post.PostDto;
import com.example.nanuer_server.dto.User.UserInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddHeartDto {
    private int userId;
    private int postId;
    private UserEntity userEntity;
    private PostEntity postEntity;

    public HeartEntity toEntity(){
        HeartEntity heartEntity = HeartEntity.builder()
                .userEntity(userEntity)
                .postEntity(postEntity)
                .build();
        return heartEntity;
    }
}
