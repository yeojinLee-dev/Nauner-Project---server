package com.example.nanuer_server.service;

import com.example.nanuer_server.config.BaseException;
import com.example.nanuer_server.domain.entity.CategoryEntity;
import com.example.nanuer_server.domain.entity.HeartEntity;
import com.example.nanuer_server.domain.entity.PostEntity;
import com.example.nanuer_server.domain.entity.UserEntity;
import com.example.nanuer_server.domain.repository.*;
import com.example.nanuer_server.dto.Post.*;
import com.example.nanuer_server.service.heart.HeartService;
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
    private final HeartRepository heartRepository;
    private final HeartService heartService;

    public List<PostEntity> getAllPosts() throws BaseException {
        return postRepository.findAllOrderByPostIdDesc();
    }

    public List<PostEntity> getPostList(int user_id, String query) throws BaseException {
        return postRepository.findAll(user_id, query);
    }

    public int createPost(CreatePostReqDto createPostReqDto) throws BaseException {
        UserEntity userEntity = userRepository.getReferenceById(createPostReqDto.getUserId());
        CategoryEntity categoryEntity = categoryRepository.getReferenceById(createPostReqDto.getCategoryId());

        createPostReqDto.setUserEntity(userEntity);
        createPostReqDto.setCategoryEntity(categoryEntity);

        return postRepository.save(createPostReqDto.toEntity()).getPostId();
    }

    @Transactional
    public GetPostResDto getPost(int post_id) throws BaseException {
        PostEntity postEntity = postRepository.findById(post_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. post_id = " + post_id));

        postEntity.increaseView();

        return new GetPostResDto(postEntity);
    }

    @Transactional
    public int updatePost(int post_id, UpdatePostReqDto updatePostReqDto) throws BaseException {
        PostEntity postEntity = postRepository.findById(post_id)
                .orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. post_id = "+ post_id));

        postEntity.update(updatePostReqDto);

        return post_id;
    }

    @Transactional
    public int deletePost(int post_id) throws BaseException {
        PostEntity postEntity = postRepository.findById(post_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. post_id = " + post_id));
        for(HeartEntity heartEntity : heartRepository.findByPostId(postEntity.getPostId())){
            heartService.deleteHeart(heartEntity.getHeartId());
        }
        postEntity.delete();
        return post_id;
    }
}