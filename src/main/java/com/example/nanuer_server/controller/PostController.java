package com.example.nanuer_server.controller;

import com.example.nanuer_server.config.BaseException;
import com.example.nanuer_server.config.BaseResponse;
import com.example.nanuer_server.domain.entity.PostEntity;
import com.example.nanuer_server.service.PostService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PostService postService;

    @GetMapping("/test")
    public String testPostController() {
        return "success";
    }

    /* 게시물 리스트 조회 */
    @GetMapping("")
    public BaseResponse<List<PostEntity>> getPosts(@RequestParam int user_id) {
        try {
            List<PostEntity> postEntities = postService.getPosts(user_id);

            return new BaseResponse<>(postEntities);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

}
