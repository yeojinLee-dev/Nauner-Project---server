package com.example.nanuer_server.domain.entity;

import com.example.nanuer_server.domain.BaseTimeEntity;
import com.example.nanuer_server.dto.like.LikeDto;
import lombok.*;

import javax.persistence.*;

@Table(name="like")
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
    private Integer likeId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name="post_id")
    @ToString.Exclude
    private PostEntity postEntity;

    public LikeDto toDto(){
        LikeDto likeDto = LikeDto.builder()
                .likeId(likeId)
                .userInfoDto(userEntity.toDto())
                .postDto(postEntity.toDto())
                .build();
        return likeDto;
    }

}