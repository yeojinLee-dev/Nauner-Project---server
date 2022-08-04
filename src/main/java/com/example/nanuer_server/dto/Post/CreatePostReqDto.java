package com.example.nanuer_server.dto.Post;

import com.example.nanuer_server.domain.Progress;
import com.example.nanuer_server.domain.entity.CategoryEntity;
import com.example.nanuer_server.domain.entity.PostEntity;
import com.example.nanuer_server.domain.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreatePostReqDto {

    private String title;
    private String content;

    @JsonProperty("cost_info")
    private String costInfo;
    private String menu;
    private int total;

    @JsonProperty("delivery_cost")
    private String deliveryCost;
    private String location;
    private String time;
    private UserEntity userEntity;
    private CategoryEntity categoryEntity;

    @JsonProperty("user_id")
    private int userId;

    @JsonProperty("category_id")
    private int categoryId;

    private Progress progress;

    private int postStatus;

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public void setCategoryEntity(CategoryEntity categoryEntity) {
        this.categoryEntity = categoryEntity;
    }

    public PostEntity toEntity() {
        return PostEntity.builder()
                .title(title)
                .content(content)
                .costInfo(costInfo)
                .menu(menu)
                .total(total)
                .deliveryCost(deliveryCost)
                .location(location)
                .time(time)
                .userEntity(userEntity)
                .categoryEntity(categoryEntity)
                .progress(progress.Recruit)
                .postStatus(1)
                .build();
    }
}
