package com.example.nanuer_server.controller.mypage;

import com.example.nanuer_server.config.BaseException;
import com.example.nanuer_server.config.BaseResponse;
import com.example.nanuer_server.dto.mypage.MyPageDto;
import com.example.nanuer_server.dto.post.PostDto;
import com.example.nanuer_server.service.mypage.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class MyPageController {
    private final MyPageService myPageService;

    @GetMapping("/{id}")
    public BaseResponse<MyPageDto> getMyPage(@PathVariable("id") String id){
        try{
             MyPageDto myPageDto = myPageService.getMyPage(id);
             return new BaseResponse<>(myPageDto);
        }
        catch(BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @GetMapping("/post")
    public BaseResponse<List<PostDto>> getMyPagePosts(@RequestParam String id){
        try{
            List<PostDto> postDtos = myPageService.getMyPagePosts(myPageService.getMyPage(id));
            return new BaseResponse<>(postDtos);
        }
        catch(BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
