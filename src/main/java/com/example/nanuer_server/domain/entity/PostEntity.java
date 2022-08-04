package com.example.nanuer_server.domain.entity;

import com.example.nanuer_server.domain.BaseTimeEntity;
import com.example.nanuer_server.domain.Progress;
import com.example.nanuer_server.dto.Post.UpdatePostReqDto;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class PostEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int postId;

    private String title;

    private String content;

    @Column(nullable = true)
    private int view;

    //**
    @Column(nullable = true)
    private int heart;

    @Enumerated(EnumType.STRING)
    private Progress progress;

    @JsonProperty("delivery_cost")
    private String costInfo;

    private String menu;

    private int total;

    @JsonProperty("delivery_cost")
    private String deliveryCost;

    private String location;

    private String time;

    @JsonProperty("post_status")
    private int postStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private UserEntity userEntity;

    @OneToOne
    @ToString.Exclude
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;

    public void update(UpdatePostReqDto updatePostReqDto) {
        this.title = updatePostReqDto.getTitle();
        this.content = updatePostReqDto.getContent();
        this.costInfo = updatePostReqDto.getCostInfo();
        this.menu = updatePostReqDto.getMenu();
        this.total = updatePostReqDto.getTotal();
        this.deliveryCost = updatePostReqDto.getDeliveryCost();
        this.time = updatePostReqDto.getTime();
        this.location = updatePostReqDto.getLocation();
        this.categoryEntity = updatePostReqDto.getCategoryEntity();
        this.progress = updatePostReqDto.getProgress();
    }

    public void delete() {
        this.postStatus = 0;
    }

    public void increaseView() {
        this.view += 1;
    }
}