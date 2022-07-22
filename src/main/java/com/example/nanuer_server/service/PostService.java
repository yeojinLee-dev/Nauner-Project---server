package com.example.nanuer_server.service;

import com.example.nanuer_server.domain.repository.PostRepository;
import com.example.nanuer_server.dto.PostGetResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    @Autowired
    private final PostRepository postRepository;

    //public List<PostGetResDto> getPosts() {
        //return postRepository.findAll();
    //}
}
