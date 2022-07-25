package com.example.nanuer_server.dto.post;

import com.example.nanuer_server.domain.Progress;
import com.example.nanuer_server.domain.entity.PostEntity;
import com.example.nanuer_server.dto.User.UserDto;
import com.example.nanuer_server.dto.category.CategoryDto;
import com.example.nanuer_server.dto.mypage.MyPageDto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {
    private Long postId;
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
    private UserDto userDto;
    private CategoryDto categoryDto;
    private MyPageDto myPageDto;

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
                .userEntity(userDto.toEntity())
                .categoryEntity(categoryDto.toEntity())
                .myPageEntity(myPageDto.toEntity())
                .build();
        return postEntity;
    }
}
