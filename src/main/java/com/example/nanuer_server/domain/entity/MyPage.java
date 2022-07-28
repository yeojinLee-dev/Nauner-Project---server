package com.example.nanuer_server.domain.entity;

import com.example.nanuer_server.domain.BaseTimeEntity;
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
@Table(name = "my_page")
public class MyPage extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "my_page_id")
    private int myPageId;

    //이름 수정
    @OneToOne(mappedBy = "myPage")
    private User user;


    @OneToMany
    @JoinColumn(name = "like_id")
    @ToString.Exclude
    private List<Like> likeEntities = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "post_id")
    @ToString.Exclude
    private List<Post> postEntities = new ArrayList<>();



}