package com.example.nanuer_server.service.mypage;

import com.example.nanuer_server.domain.entity.LikeEntity;
import com.example.nanuer_server.domain.entity.PostEntity;
import com.example.nanuer_server.domain.repository.LikeRepository;
import com.example.nanuer_server.domain.repository.UserRepository;
import com.example.nanuer_server.dto.Post.PostDto;
import com.example.nanuer_server.dto.like.LikeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final UserRepository userRepository;

    private final LikeRepository likeRepository;

    private  final EntityManager em;

    //Post CRUD를 postService에서 관리하고, getPosts는 마이페이지에서 하는 게 낫지 않나
    public List<PostDto> getPosts(int userId){
        List<PostEntity> postEntityList = userRepository.findById(userId).get().getPostEntities();
        List<PostDto> postDtoList = postEntityList.stream()
                .map(PostEntity::toDto)
                .collect(Collectors.toList());
        return postDtoList;
    }

    // like를 어떤 식으로...? 찜한 게시물을 보여주는 건데 그러면 게시물에
    public List<PostDto> getLikePosts(int userId){
        List<LikeDto> likeDtoList = likeRepository.findAllByUserId(userId).stream()
                .map(LikeEntity::toDto)
                .collect(Collectors.toList());

        List<PostDto> postDtoList = likeDtoList.stream()
                .map(LikeDto::getPostDto)
                .collect(Collectors.toList());
        return postDtoList;
    }



}
