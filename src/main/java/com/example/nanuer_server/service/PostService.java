package com.example.nanuer_server.service;

import com.example.nanuer_server.config.BaseException;
import com.example.nanuer_server.domain.entity.Category;
import com.example.nanuer_server.domain.entity.Post;
import com.example.nanuer_server.domain.entity.User;
import com.example.nanuer_server.domain.repository.*;
import com.example.nanuer_server.dto.Post.*;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public List<GetPostListResDto> getPostList(int user_id, String query) throws BaseException {
        List<GetPostListResDto> posts = new ArrayList<>();

        List<Post> entities = postRepository.findAll(user_id, 1, query);
        for (Post entity : entities) posts.add(new GetPostListResDto(entity));

        return posts;
    }

    public int createPost(CreatePostReqDto createPostReqDto) throws BaseException {
        User user = userRepository.getReferenceById(createPostReqDto.getUserId());
        Category category = categoryRepository.getReferenceById(createPostReqDto.getCategoryId());

        createPostReqDto.setUser(user);
        createPostReqDto.setCategory(category);

        return postRepository.save(createPostReqDto.toEntity()).getPostId();
    }

    @Transactional
    public GetPostResDto getPost(int post_id) throws BaseException {
        Post post = postRepository.findById(post_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. post_id = " + post_id));

        post.increaseView();

        return new GetPostResDto(post);
    }

    @Transactional
    public int updatePost(int post_id, UpdatePostReqDto updatePostReqDto) throws BaseException {
        Post post = postRepository.findById(post_id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. post_id = "+ post_id));

        post.update(updatePostReqDto);

        return post_id;
    }

    @Transactional
    public int deletePost(int post_id) throws BaseException {
        Post post = postRepository.findById(post_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. post_id = " + post_id));
        post.delete();

        return post_id;
    }
}
