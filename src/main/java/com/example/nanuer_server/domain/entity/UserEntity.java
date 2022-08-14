package com.example.nanuer_server.domain.entity;

import com.example.nanuer_server.domain.BaseTimeEntity;
import com.example.nanuer_server.dto.User.UserInfoDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Table(name="user")
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
    private int userId;

    @Column(nullable = false,unique = true)
    private String email;

    private String name;

    @Column(nullable = false)
    private String password;

    @Column(name= "nick_name" ,nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String birth;

    @Column(name = "profile_img")
    private String profileImg;

    @Column(nullable = false)
    private String university;

    @Column(nullable = false)
    private String userStatus;

    @ColumnDefault("0")
    private int userScore;

    //@Column(s) not allowed on a @OneToOne property 발생
    //@Column(name = "my_page_entity")

    //mapped 이름 수정
    @JsonIgnore
    @OneToMany(mappedBy = "userEntity")
    private List<PostEntity> postEntities = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private UserRole role;

    /*
    public static UserEntity createUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userEntity.getId());
        userEntity.setName(userEntity.getName());
        userEntity.setNickName(userEntity.getNickName());
        userEntity.setEmail(userEntity.getEmail());
        userEntity.setPhone(userEntity.getPhone());
        userEntity.setBirth(userEntity.getBirth());
        userEntity.setProfileImg(userEntity.getProfileImg());
        userEntity.setUserStatus(userEntity.getUserStatus());
        userEntity.setUserScore(userEntity.getUserScore());
        userEntity.setArea(userEntity.getArea());
        //String password = passwordEncoder.encode(memberDto.getPassword());
        //userEntity.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        return userEntity;
    }*/

    public void status(String status){
        this.userStatus  = status;
    }

    public void upDatePw(String password){
        this.password = password;
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
        if(userId >0)
            return true;
        else
            return false;
    }


}