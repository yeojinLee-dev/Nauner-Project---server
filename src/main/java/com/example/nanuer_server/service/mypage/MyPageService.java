package com.example.nanuer_server.service.mypage;

import com.example.nanuer_server.domain.entity.HeartEntity;
import com.example.nanuer_server.domain.entity.PostEntity;
import com.example.nanuer_server.domain.repository.HeartRepository;
import com.example.nanuer_server.domain.repository.UserRepository;
import com.example.nanuer_server.dto.Post.PostDto;
import com.example.nanuer_server.dto.heart.HeartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final UserRepository userRepository;

    private final HeartRepository heartRepository;


    //Post CRUD를 postService에서 관리하고, 내가 쓴 글 조회하는 getPosts는 마이페이지에서 하는 게 낫지 않나
    @Transactional
    public List<PostDto> getPosts(int userId){
        List<PostEntity> postEntityList = userRepository.findById(userId).get().getPostEntities();
        List<PostDto> postDtoList = postEntityList.stream()
                .map(PostEntity::toDto)
                .collect(Collectors.toList());
        return postDtoList;
    }

    // like를 어떤 식으로...? 찜한 게시물을 보여주는 건데 그러면 게시물에
    @Transactional
    public List<PostDto> getHeartPosts(int userId){
        List<HeartDto> heartDtoList = heartRepository.findAll(userId).stream()
                .map(HeartEntity::toDto)
                .collect(Collectors.toList());

        List<PostDto> postDtoList = heartDtoList.stream()
                .map(HeartDto::getPostDto)
                .collect(Collectors.toList());
        return postDtoList;
    }

}
