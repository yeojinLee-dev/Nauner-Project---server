package com.example.nanuer_server.service.mypage;

import com.example.nanuer_server.config.BaseException;
import com.example.nanuer_server.domain.entity.UserEntity;
import com.example.nanuer_server.domain.repository.UserRepository;
import com.example.nanuer_server.dto.Post.PostDto;
import com.example.nanuer_server.dto.User.UserInfoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MyPageServiceTest {
    @Autowired
    private MyPageService myPageService;

    @Autowired
    private UserRepository userRepository;


    @Test
    void getPosts(){
        List<PostDto> postDtoList = myPageService.getPosts(3);
        postDtoList.forEach(System.out::println);
    }

    @Test
    void updateUserTest(){
        UserInfoDto beforeUserInfoDto = userRepository.findByEmail("dongwoo0307@naver.com").get().toDto();
        UserInfoDto updatedUserInfoDto = beforeUserInfoDto;
        try {
            UserInfoDto userInfoDto = myPageService.updateUser(updatedUserInfoDto,"dongwoo0307@naver.com" );
            System.out.println(userInfoDto.toString());
        } catch (BaseException exception){
            System.out.println(exception.getMessage());
        }
    }

}