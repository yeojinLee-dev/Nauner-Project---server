package com.example.nanuer_server.domain.entity;

import com.example.nanuer_server.domain.BaseTimeEntity;
import com.example.nanuer_server.dto.User.UserInfoDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@DynamicUpdate
@DynamicInsert
@Entity
@ToString(callSuper = true) // 부모 클래스의 toString 불러오는 어노테이션. 붙이면 createdAt 하고 updatedAt 데이터 정상적으로 나옴.
@EqualsAndHashCode(callSuper = true) // 부모클래스의 equalsAndHashCode 불러오는 어노테이션.
public class UserEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;


    @Column(nullable = false,unique = true)
    private String email;

    private String name;

    @Column(nullable = false)
    private String password;

    private String nickName;

    @Column(nullable = false)
    private String phone;

    private String birth;

    @Column
    private String profileImg;

    private String university;

    @Column(nullable = false)
    private String userStatus;

    @Column(name = "user_score")
    private int userScore;

    //@Column(s) not allowed on a @OneToOne property 발생
    //@Column(name = "my_page_entity")

    //mapped 이름 수정
    @JsonIgnore
    @OneToMany(mappedBy = "userEntity")
    @Column(name = "posts")
    private List<PostEntity> postEntities = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private UserRole role;


    public void status(String status){
        this.userStatus  = status;

    }
    public UserInfoDto toDto(){
        UserInfoDto userInfoDto = UserInfoDto.builder()
                .password(password)
                .name(name)
                .nickName(nickName)
                .email(email)
                .phone(phone)
                .birth(birth)
                .profileImg(profileImg)
                .university(university)
                .userStatus(userStatus)
                .role(role)
                .build();
        return userInfoDto;
    }

    public boolean isPresent(){
        if(userId != null)
            return true;
        else
            return false;
    }


}