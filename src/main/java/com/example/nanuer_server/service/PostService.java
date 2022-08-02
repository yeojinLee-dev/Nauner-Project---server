package com.example.nanuer_server.service;

import com.example.nanuer_server.config.BaseException;
import com.example.nanuer_server.domain.entity.Category;
import com.example.nanuer_server.domain.entity.Post;
import com.example.nanuer_server.domain.entity.User;
import com.example.nanuer_server.domain.repository.*;
import com.example.nanuer_server.dto.Post.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public List<GetPostListResDto> getPostList(int user_id, String query) throws BaseException {
        List<GetPostListResDto> posts = new ArrayList<>();

        List<Post> entities = postRepository.findAll(user_id, query, "'ACTIVE'");
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

    public GetPostResDto getPost(int post_id) throws BaseException {
        /* 게시글 조회 수 증가 */
        int view = postRepository.findViewByPostId(post_id);
        postRepository.saveView(view+1, post_id);

        Post entity = postRepository.findById(post_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. post_id = " + post_id));

        return new GetPostResDto(entity);
    }

    public int deletePost(int post_id) throws BaseException {
        postRepository.savePostStatus("INACTIVE", post_id);
        return post_id;
    }
}
