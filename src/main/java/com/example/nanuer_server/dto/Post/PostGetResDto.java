package com.example.nanuer_server.dto.Post;

import com.example.nanuer_server.domain.entity.CategoryEntity;
import lombok.*;
import com.example.nanuer_server.domain.entity.UserEntity;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostGetResDto {

    private int post_id;
    private String title;
    private int view;
    private String progress;
    private int total;
    private String location;
    private LocalDateTime modified_date;
    private UserEntity user;
    private CategoryEntity category;

    @Builder
    public PostGetResDto(int post_id, String title, int view, String progress, int total, String location,
                         LocalDateTime modified_date, UserEntity user, CategoryEntity category) {
        this.post_id = post_id;
        this.title = title;
        this.view = view;
        this.progress = progress;
        this.total = total;
        this.location = location;
        this.modified_date = modified_date;
        this.user = user;
        this.category = category;
    }

    public PostGetResDto toEntity() {
        return PostGetResDto.builder()
                .post_id(post_id)
                .title(title)
                .view(view)
                .progress(progress)
                .total(total)
                .location(location)
                .modified_date(modified_date)
                .user(user)
                .category(category)
                .build();
    }

}


