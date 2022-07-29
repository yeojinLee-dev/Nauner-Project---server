package com.example.nanuer_server.dto.Post;

import com.example.nanuer_server.domain.entity.Category;
import lombok.*;
import com.example.nanuer_server.domain.entity.UserEntity;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostGetResDto {

    private Integer post_id;
    private String title;
    private int view;
    private String progress;
    private int total;
    private String location;
    private LocalDateTime modified_date;
    private UserEntity userEntity;
    private Category category;

    @Builder
    public PostGetResDto(int post_id, String title, int view, String progress, int total, String location,
                         LocalDateTime modified_date, UserEntity userEntity, Category category) {
        this.post_id = post_id;
        this.title = title;
        this.view = view;
        this.progress = progress;
        this.total = total;
        this.location = location;
        this.modified_date = modified_date;
        this.userEntity = userEntity;
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
                .userEntity(userEntity)
                .category(category)
                .build();
    }

}


