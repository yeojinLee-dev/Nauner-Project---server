package com.example.nanuer_server.controller.mypage;

import com.example.nanuer_server.config.BaseException;
import com.example.nanuer_server.config.BaseResponse;
import com.example.nanuer_server.dto.Post.PostDto;
import com.example.nanuer_server.dto.User.UserInfoDto;
import com.example.nanuer_server.service.mypage.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {
    private final MyPageService myPageService;

    @GetMapping("/my-posts")
    public BaseResponse<List<PostDto>> getMyPosts(@RequestParam String email){
        List<PostDto> postDtoList = myPageService.getMyPosts(email);
        return new BaseResponse<>(postDtoList);
    }

    @GetMapping("/heart-posts")
    public BaseResponse<List<PostDto>> getHeartPosts(@RequestParam String email){
        List<PostDto> postDtoList = myPageService.getHeartPosts(email);
        return new BaseResponse<>(postDtoList);
    }

    @PatchMapping("/update-user")
    public BaseResponse<UserInfoDto> patchUpdatedUser(@RequestBody UserInfoDto userInfoDto,@RequestParam String email){
        try {
            var updatedUser = myPageService.updateUser(userInfoDto, email);
            return new BaseResponse<>(updatedUser);
        } catch(BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

}
