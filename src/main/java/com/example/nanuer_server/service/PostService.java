package com.example.nanuer_server.service;

import com.example.nanuer_server.config.BaseException;
import com.example.nanuer_server.domain.entity.Post;
import com.example.nanuer_server.domain.repository.PostRepository;
import com.example.nanuer_server.dto.Post.PostGetResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    @Autowired
    private final PostRepository postRepository;

    public List<PostGetResDto> getPosts(int user_id) throws BaseException {
        List<PostGetResDto> posts = new ArrayList<>();
        List<Post> entities = postRepository.findAll(user_id);

        for (Post entity : entities) posts.add(new PostGetResDto(entity));

        return posts;
    }
}
