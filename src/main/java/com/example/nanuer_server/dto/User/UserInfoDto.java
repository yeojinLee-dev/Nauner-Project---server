package com.example.nanuer_server.dto.User;

import com.example.nanuer_server.domain.entity.UserEntity;
import com.example.nanuer_server.domain.entity.UserRole;
import com.example.nanuer_server.dto.Post.PostDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Nullable;
import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfoDto {
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

    private List<PostDto> postDtoList = new ArrayList<>();

    public UserEntity toEntity() {
        UserEntity userEntity = UserEntity.builder()
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
