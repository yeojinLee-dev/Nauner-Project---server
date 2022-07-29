package com.example.nanuer_server.dto.Post;

import com.example.nanuer_server.domain.Progress;
import com.example.nanuer_server.domain.entity.PostEntity;
import com.example.nanuer_server.dto.User.UserInfoDto;
import com.example.nanuer_server.dto.category.CategoryDto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {
    private Integer postId;
    private String title;
    private String content;
    private int view;
    private int like;
    private Progress progress;
    private String costInfo;
    private int total;
    private String deliveryCost;
    private String location;
    private String time;
    private String postStatus;
    private UserInfoDto userInfoDto;
    private CategoryDto categoryDto;

    public PostEntity toEntity(){
        PostEntity postEntity = PostEntity.builder()
                .postId(postId)
                .title(title)
                .content(content)
                .view(view)
                .like(like)
                .progress(progress)
                .costInfo(costInfo)
                .total(total)
                .deliveryCost(deliveryCost)
                .location(location)
                .time(time)
                .postStatus(postStatus)
                .userEntity(userInfoDto.toEntity())
                .category(categoryDto.toEntity())
                .build();
        return postEntity;
    }
}
