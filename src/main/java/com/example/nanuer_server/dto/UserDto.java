package com.example.nanuer_server.dto;

import com.example.nanuer_server.domain.entity.UserEntity;
import com.example.nanuer_server.domain.entity.UserRole;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String id;
    private String password;
    private String name;
    private String email;
    private String nickName;
    private String phone;
    private String birth;
    private String profileImg;
    private String university;
    private String userStatus;
    private int  userScore;
    private UserRole role;

    /* DTO -> Entity */
    public UserEntity toEntity() {
        UserEntity userEntity = UserEntity.builder()
                .id(id)
                .password(password)
                .name(name)
                .nickName(nickName)
                .email(email)
                .phone(phone)
                .birth(birth)
                .profileImg(profileImg)
                .university(university)
                .userStatus(userStatus)
                .userScore(userScore)
                .role(role)
                .build();
        return userEntity;
    }


}
