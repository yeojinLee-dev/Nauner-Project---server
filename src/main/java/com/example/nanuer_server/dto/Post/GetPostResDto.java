package com.example.nanuer_server.dto.Post;

import com.example.nanuer_server.domain.Progress;
import com.example.nanuer_server.domain.entity.CategoryEntity;
import com.example.nanuer_server.domain.entity.PostEntity;
import com.example.nanuer_server.domain.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetPostResDto {

    private int postId;
    private String title;
    private String content;
    private int view;
    private int heart;
    private Progress progress;
    private String costInfo;
    private String menu;
    private int total;
    private String deliveryCost;
    private String location;
    private String time;
    private int postStatus;
    private UserEntity userEntity;
    private CategoryEntity categoryEntity;

    @Builder
    public GetPostResDto(PostEntity entity) {
        this.postId = entity.getPostId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.view = entity.getView();
        this.heart = entity.getHeart();
        this.progress = entity.getProgress();
        this.costInfo = entity.getCostInfo();
        this.menu = entity.getMenu();
        this.total = entity.getTotal();
        this.deliveryCost = entity.getDeliveryCost();
        this.location = entity.getLocation();
        this.time = entity.getTime();
        this.postStatus = entity.getPostStatus();
        this.userEntity = entity.getUserEntity();
        this.categoryEntity = entity.getCategoryEntity();
    }
}
