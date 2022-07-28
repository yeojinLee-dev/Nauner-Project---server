package com.example.nanuer_server.domain.entity;

import com.example.nanuer_server.domain.BaseTimeEntity;
import com.example.nanuer_server.domain.Progress;
import com.example.nanuer_server.dto.Post.PostGetResDto;
import lombok.*;

import javax.persistence.*;

@Table(name = "post")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@ToString(callSuper = true) // 부모 클래스의 toString 불러오는 어노테이션. 붙이면 createdAt 하고 updatedAt 데이터 정상적으로 나옴.
@EqualsAndHashCode(callSuper = true) // 부모클래스의 equalsAndHashCode 불러오는 어노테이션.
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int postId;

    private String title;

    private String content;

    private int view;

    //**
    private int like;

    @Enumerated(EnumType.STRING)
    private Progress progress;

    private String costInfo;

    private int total;

    private String deliveryCost;

    private String location;

    private String time;

    private String post_status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    //@Column(name = "user_id")
    @ToString.Exclude
    private User user;

    @OneToOne
    @ToString.Exclude
    @JoinColumn(name = "category_id")
    private Category category;

}