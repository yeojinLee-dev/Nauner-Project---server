package com.example.nanuer_server.service;

import com.example.nanuer_server.config.BaseException;
import com.example.nanuer_server.domain.entity.PostEntity;
import com.example.nanuer_server.domain.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<PostEntity> getPosts(int user_id) throws BaseException {
        return postRepository.findAll(user_id);
    }
}
