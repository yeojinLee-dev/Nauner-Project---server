package com.example.nanuer_server.dto.category;

import com.example.nanuer_server.domain.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    private Integer categoryId;
    private String categoryName;

    public Category toEntity(){
        Category category = Category.builder()
                .categoryId(categoryId)
                .categoryName(categoryName)
                .build();
        return category;
    }
}
