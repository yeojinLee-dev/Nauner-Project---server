package com.example.nanuer_server.domain.entity;

import com.example.nanuer_server.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Table(name="category")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class CategoryEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int categoryId;

    @Column(name="category_name",nullable = false)
    private String categoryName;

    //일대일, 양방향, 이름 수정 (mapped에는 테이블 이름을 앞에 소문자로 적어야하는듯..싶은..)
    //@OneToOne(mappedBy = "categoryEntity")
    //private PostEntity postEntity;
}
