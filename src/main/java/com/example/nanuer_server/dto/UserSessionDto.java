package com.example.nanuer_server.dto;

import com.example.nanuer_server.domain.entity.UserEntity;
import com.example.nanuer_server.domain.entity.UserRole;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class UserSessionDto implements Serializable {

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

    /* Entity -> Dto */
    public UserSessionDto(UserEntity user) {
        this.id = user.getId();
        this.password = user.getPassword();
        this.name = user.getName();
        this.email = user.getEmail();
        this.nickName = user.getNickName();
        this.phone = user.getPhone();
        this.birth = user.getBirth();
        this.profileImg = user.getProfileImg();
        this.university = user.getUniversity();
        this.userStatus = user.getUserStatus();
        this.userScore = user.getUserScore();
        this.role = user.getRole();
        //this.role = user.getRole();
    }
}