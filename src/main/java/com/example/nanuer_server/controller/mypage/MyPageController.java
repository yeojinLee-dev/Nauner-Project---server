package com.example.nanuer_server.controller.mypage;

import com.example.nanuer_server.config.BaseException;
import com.example.nanuer_server.config.BaseResponse;
import com.example.nanuer_server.config.User.JwtTokenProvider;
import com.example.nanuer_server.dto.Post.PostDto;
import com.example.nanuer_server.dto.User.UserInfoDto;
import com.example.nanuer_server.service.User.UserService;
import com.example.nanuer_server.service.mypage.MyPageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MyPageController {
    private final MyPageService myPageService;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;


    @GetMapping("/my-posts")
    public BaseResponse<List<PostDto>> getMyPosts(HttpServletRequest request){
        String token = request.getHeader("X-AUTH-TOKEN");
        String email = jwtTokenProvider.getUserPk(token);
        List<PostDto> postDtoList = myPageService.getMyPosts(email);
        return new BaseResponse<>(postDtoList);
    }

    @GetMapping("/heart-posts")
    public BaseResponse<List<PostDto>> getHeartPosts(HttpServletRequest request){
        String token = request.getHeader("X-AUTH-TOKEN");
        String email = jwtTokenProvider.getUserPk(token);
        List<PostDto> postDtoList = myPageService.getHeartPosts(email);
        return new BaseResponse<>(postDtoList);
    }

    @PatchMapping("/update-user")
    public BaseResponse<UserInfoDto> updatedUser(HttpServletRequest request,@RequestBody UserInfoDto userInfoDto){
        String token = request.getHeader("X-AUTH-TOKEN");
        String email = jwtTokenProvider.getUserPk(token);
        try {
            var updatedUser = myPageService.updateUser(userInfoDto, email);
            return new BaseResponse<>(updatedUser);
        } catch(BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

}
