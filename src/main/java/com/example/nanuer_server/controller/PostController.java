package com.example.nanuer_server.controller;

import com.example.nanuer_server.dto.PostGetResDto;
import com.example.nanuer_server.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    @Autowired
    private final PostService postService;

//    @GetMapping("")
//    public BaseResponse<List<PostGetResDto>> getPosts() {
//
//    }

}
