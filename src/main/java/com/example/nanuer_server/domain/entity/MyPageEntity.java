package com.example.nanuer_server.domain.entity;

import com.example.nanuer_server.domain.BaseTimeEntity;
import com.example.nanuer_server.dto.mypage.MyPageDto;
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
public class MyPageEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "my_page_id")
    private int myPageId;

    //이름 수정
    @OneToOne(mappedBy = "myPageEntity")
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

//    @OneToMany
//    @JoinColumn(name = "my_page_entity")
//    @ToString.Exclude
//    private List<LikeEntity> likeEntities = new ArrayList<>();

//    @OneToMany
//    @JoinColumn(name = "my_page_entity")
//    @ToString.Exclude
//    private List<PostEntity> postEntities = new ArrayList<>();

    public MyPageDto toDto(){
        MyPageDto myPageDto = MyPageDto.builder()
                .myPageId(myPageId)
                .userDto(userEntity.toDto())
                .build();
        return myPageDto;
    }

}