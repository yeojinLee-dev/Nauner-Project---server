package com.example.nanuer_server.service.mypage;

import com.example.nanuer_server.dto.Post.PostDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MyPageServiceTest {
    @Autowired
    private MyPageService myPageService;

    @Test
    void getLikePage(){
        List<PostDto> postDtoList = myPageService.getLikePosts(10);
        postDtoList.forEach(System.out::println);
    }

}