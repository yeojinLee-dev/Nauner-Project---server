package com.example.nanuer_server.domain.entity;

import com.example.nanuer_server.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@ToString(callSuper = true) // 부모 클래스의 toString 불러오는 어노테이션. 붙이면 createdAt 하고 updatedAt 데이터 정상적으로 나옴.
@EqualsAndHashCode(callSuper = true) // 부모클래스의 equalsAndHashCode 불러오는 어노테이션.
public class LikeEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int LikeId;

    @ManyToOne
    @JoinColumn(name = "my_page_entity")
    @ToString.Exclude
    private MyPageEntity myPageEntity;

    @ManyToOne
    @JoinColumn(name = "user_entity")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name="post_entity")
    @ToString.Exclude
    private PostEntity postEntity;

}