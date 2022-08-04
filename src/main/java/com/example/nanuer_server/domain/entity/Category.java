package com.example.nanuer_server.domain.entity;

import com.example.nanuer_server.domain.BaseTimeEntity;
import com.example.nanuer_server.dto.category.CategoryDto;
import lombok.*;

import javax.persistence.*;

@Table(name="category")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@ToString(callSuper = true) // 부모 클래스의 toString 불러오는 어노테이션. 붙이면 createdAt 하고 updatedAt 데이터 정상적으로 나옴.
@EqualsAndHashCode(callSuper = true) // 부모클래스의 equalsAndHashCode 불러오는 어노테이션.
public class Category extends BaseTimeEntity{
    @Id
    @Column(name="category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @Column(name="category_name",nullable = false)
    private String categoryName;

    //일대일, 양방향, 이름 수정 (mapped에는 테이블 이름을 앞에 소문자로 적어야하는듯..싶은..)
    //@OneToOne(mappedBy = "categoryEntity")
    //private PostEntity postEntity;

    public CategoryDto toDto(){
        CategoryDto categoryDto = CategoryDto.builder()
                .categoryId(categoryId)
                .categoryName(categoryName)
                .build();
        return categoryDto;
    }
}
