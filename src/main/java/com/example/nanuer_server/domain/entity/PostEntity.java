package com.example.nanuer_server.domain.entity;

import com.example.nanuer_server.domain.BaseTimeEntity;
import com.example.nanuer_server.domain.Progress;
import com.example.nanuer_server.dto.post.PostDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.persistence.*;

@Table(name = "Post")
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
    @Column(name = "post_id")
    private Long postId;

    private String title;

    private String content;

    private int view;
    //**

    private int like;

    @Enumerated(EnumType.STRING)
    private Progress progress;

    @Column(name = "cost_info")
    private String costInfo;

    private int total;

    @Column(name = "delivery_cost")
    private String deliveryCost;

    private String location;

    private String time;

    @Column(name = "post_status")
    private String postStatus;


    //@Column(s) not allowed on a @OneToOne property 발생
    //@Column(name = "user_entity")
    @JoinColumn(name = "user_id")
    @ManyToOne
    @ToString.Exclude
    private UserEntity userEntity;


    //@Column(s) not allowed on a @OneToOne property 발생
    @JoinColumn(name = "category_id")
    @OneToOne
    private CategoryEntity categoryEntity;



    @ManyToOne
    @JoinColumn(name="my_page_id")
    @ToString.Exclude
    private MyPageEntity myPageEntity;

    public PostDto toDto(){
        PostDto postDto = PostDto.builder()
                .postId(postId)
                .title(title)
                .content(content)
                .view(view)
                .like(like)
                .progress(progress)
                .costInfo(costInfo)
                .total(total)
                .deliveryCost(deliveryCost)
                .location(location)
                .time(time)
                .userDto(userEntity.toDto())
                .categoryDto(categoryEntity.toDto())
                .myPageDto(myPageEntity.toDto())
                .build();
        return postDto;
    }
}