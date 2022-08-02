package com.example.nanuer_server.domain.entity;

import com.example.nanuer_server.domain.BaseTimeEntity;
import com.example.nanuer_server.domain.Progress;
import com.example.nanuer_server.dto.Post.PostDto;
import lombok.*;

import javax.persistence.*;

@Table(name = "post")
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
    private Integer postId;

    private String title;

    private String content;

    private int view;
    //**

    private int heart;

    @Enumerated(EnumType.STRING)
    private Progress progress;

    @Column(name = "cost_info")
    private String costInfo;

    private int total;

    @Column(name = "delivery_cost")
    private String deliveryCost;

    private String location;

    private String time;

    private String postStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private UserEntity userEntity;

    @ToString.Exclude
    @JoinColumn(name = "category_id")
    @OneToOne
    private Category category;


    public PostDto toDto(){
        PostDto postDto = PostDto.builder()
                .postId(postId)
                .title(title)
                .content(content)
                .view(view)
                .heart(heart)
                .progress(progress)
                .costInfo(costInfo)
                .total(total)
                .deliveryCost(deliveryCost)
                .location(location)
                .time(time)
                .postStatus(postStatus)
                .userInfoDto(userEntity.toDto())
                .categoryDto(category.toDto())
                .build();
        return postDto;

    }
}