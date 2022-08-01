package com.example.nanuer_server.dto.Post;

import com.example.nanuer_server.domain.Progress;
import com.example.nanuer_server.domain.entity.Category;
import com.example.nanuer_server.domain.entity.Post;
import lombok.*;
import com.example.nanuer_server.domain.entity.User;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class GetPostListResDto {

    private int postId;
    private String title;
    private int view;
    private Progress progress;
    private int total;
    private String location;
    private LocalDateTime modifiedDate;
    private User user;
    private Category category;

    @Builder
    public GetPostListResDto(Post entity) {
        this.postId = entity.getPostId();
        this.title = entity.getTitle();
        this.view = entity.getView();
        this.progress = entity.getProgress();
        this.total = entity.getTotal();
        this.location = entity.getLocation();
        this.modifiedDate = entity.getModified_date();
        this.user = entity.getUser();
        this.category = entity.getCategory();
    }

}


