package com.example.nanuer_server.domain.entity;

import com.example.nanuer_server.domain.BaseTimeEntity;
import com.example.nanuer_server.domain.Progress;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@ToString(callSuper = true) // 부모 클래스의 toString 불러오는 어노테이션. 붙이면 createdAt 하고 updatedAt 데이터 정상적으로 나옴.
@EqualsAndHashCode(callSuper = true) // 부모클래스의 equalsAndHashCode 불러오는 어노테이션.
public class PostEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_entity")
    private int postId;

    private int categoryId;

    private String title;

    private String content;

    private int view;

    //**
    private int like;

    private Progress progress;

    private String costInfo;

    private int total;

    private String deliveryCost;

    private String location;

    private String time;

    private String post_status;

    //@ManyToOne
    //@JoinColumn(name = "post_entities")
    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @OneToOne
    @ToString.Exclude
    @JoinColumn(name = "category_entity")
    private CategoryEntity categoryEntity;


    @ManyToOne
    @JoinColumn(name="my_page_entity")
    @ToString.Exclude
    private MyPageEntity myPageEntity;

}