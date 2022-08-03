package com.example.nanuer_server.dto.Post;

import com.example.nanuer_server.domain.Progress;
import com.example.nanuer_server.domain.entity.Category;
import com.example.nanuer_server.domain.entity.Post;
import com.example.nanuer_server.domain.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public class UpdatePostReqDto {

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

    private Category category;

    @JsonProperty("category_id")
    private int categoryId;

    private Progress progress;

    public void setCategory(Category category) {
        this.category = category;
    }
}
