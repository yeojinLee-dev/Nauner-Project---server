package com.example.nanuer_server.service;

import com.example.nanuer_server.config.BaseException;
import com.example.nanuer_server.domain.entity.Category;
import com.example.nanuer_server.domain.entity.Post;
import com.example.nanuer_server.domain.entity.User;
import com.example.nanuer_server.domain.repository.CategoryRepository;
import com.example.nanuer_server.domain.repository.PostRepository;
import com.example.nanuer_server.domain.repository.UserRepository;
import com.example.nanuer_server.dto.Post.CreatePostReqDto;
import com.example.nanuer_server.dto.Post.GetPostsResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public List<GetPostsResDto> getPosts(int user_id) throws BaseException {
        List<GetPostsResDto> posts = new ArrayList<>();

        List<Post> entities = postRepository.findAll(user_id);
        for (Post entity : entities) posts.add(new GetPostsResDto(entity));

        return posts;
    }

    public int createPost(CreatePostReqDto createPostReqDto) throws BaseException {
        User user = userRepository.getReferenceById(createPostReqDto.getUserId());
        Category category = categoryRepository.getReferenceById(createPostReqDto.getCategoryId());

        createPostReqDto.setUser(user);
        createPostReqDto.setCategory(category);

        return postRepository.save(createPostReqDto.toEntity()).getPostId();
    }
}
