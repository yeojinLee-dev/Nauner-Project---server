package com.example.nanuer_server.service;

import com.example.nanuer_server.config.BaseException;
import com.example.nanuer_server.domain.entity.PostEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PostEntityServiceTest {
    @Autowired
    private PostService postService;

    @Test
    void PostTest(){
        try {
            List<PostEntity> postEntityList = postService.getPosts(3);
            postEntityList.forEach(System.out::println);
        }catch(BaseException exception){
            System.out.println(exception.getMessage());
        }
    }

}