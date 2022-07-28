package com.example.nanuer_server.service.mypage;

import com.example.nanuer_server.config.BaseException;
import com.example.nanuer_server.dto.mypage.MyPageDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MyPageServiceTest {

    @Autowired
    private MyPageService myPageService;

    @Test
    void getMyPageTest(){
        try{
            MyPageDto myPageDto = myPageService.getMyPage("kidzero3");
            System.out.println("myPageId : " + myPageDto.getMyPageId());
        } catch(BaseException exception){
            System.out.println(exception.getMessage());
        }
    }

}