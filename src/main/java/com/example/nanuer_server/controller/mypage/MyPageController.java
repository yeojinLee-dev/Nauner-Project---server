package com.example.nanuer_server.controller.mypage;

import com.example.nanuer_server.config.BaseException;
import com.example.nanuer_server.config.BaseResponse;
import com.example.nanuer_server.dto.mypage.MyPageDto;
import com.example.nanuer_server.service.mypage.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class MyPageController {
    private final MyPageService myPageService;
/*
    @GetMapping("/{email}")
    public BaseResponse<MyPageDto> getMyPage(@PathVariable("email") String email){
        try{
             MyPageDto myPageDto = myPageService.getMyPage(email);
             return new BaseResponse<>(myPageDto);
        }
        catch(BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }*/
}
