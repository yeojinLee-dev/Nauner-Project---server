package com.example.nanuer_server.dto.Post;

import com.example.nanuer_server.domain.Progress;
import com.example.nanuer_server.domain.entity.Category;
import com.example.nanuer_server.domain.entity.Post;
import com.example.nanuer_server.domain.entity.User;
import com.example.nanuer_server.domain.repository.CategoryRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

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
    private User user;
    private Category category;

    @JsonProperty("user_id")
    private int userId;

    @JsonProperty("category_id")
    private int categoryId;

    private Progress progress;

    private String postStatus;

    public void setUser(User user) {
        this.user = user;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Post toEntity() {
        return Post.builder()
                .title(title)
                .content(content)
                .costInfo(costInfo)
                .menu(menu)
                .total(total)
                .deliveryCost(deliveryCost)
                .location(location)
                .time(time)
                .user(user)
                .category(category)
                .progress(progress.Recruit)
                .postStatus("ACTIVE")
                .build();
    }
}
