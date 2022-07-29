package com.example.nanuer_server.dto.like;

import com.example.nanuer_server.domain.entity.LikeEntity;
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
public class LikeDto {
    private Integer likeId;
    private UserInfoDto userInfoDto;
    private PostDto postDto;

    public LikeEntity toEntity(){
        LikeEntity likeEntity = LikeEntity.builder()
                .likeId(likeId)
                .userEntity(userInfoDto.toEntity())
                .postEntity(postDto.toEntity())
                .build();
        return likeEntity;
    }
}
