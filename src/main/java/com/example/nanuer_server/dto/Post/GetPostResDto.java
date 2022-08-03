package com.example.nanuer_server.dto.Post;

import com.example.nanuer_server.domain.Progress;
import com.example.nanuer_server.domain.entity.Category;
import com.example.nanuer_server.domain.entity.Post;
import com.example.nanuer_server.domain.entity.User;
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
    private User user;
    private Category category;

    @Builder
    public GetPostResDto(Post entity) {
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
        this.user = entity.getUser();
        this.category = entity.getCategory();
    }
}
