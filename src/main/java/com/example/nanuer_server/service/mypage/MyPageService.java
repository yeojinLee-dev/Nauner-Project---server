package com.example.nanuer_server.service.mypage;

import com.example.nanuer_server.config.BaseException;
import com.example.nanuer_server.domain.entity.HeartEntity;
import com.example.nanuer_server.domain.entity.PostEntity;
import com.example.nanuer_server.domain.entity.UserEntity;
import com.example.nanuer_server.domain.repository.HeartRepository;
import com.example.nanuer_server.domain.repository.UserRepository;
import com.example.nanuer_server.dto.Post.PostDto;
import com.example.nanuer_server.dto.User.UserInfoDto;
import com.example.nanuer_server.dto.heart.HeartDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.nanuer_server.config.BaseResponseStatus.USERS_EMPTY_USER_EMAIL;

@Service
@Transactional
@RequiredArgsConstructor
public class MyPageService {
    private final UserRepository userRepository;

    private final HeartRepository heartRepository;


    //Post CRUD를 postService에서 관리하고, 내가 쓴 글 조회하는 getPosts는 마이페이지에서 하는 게 낫지 않나
    public List<PostDto> getMyPosts(String email){
        List<PostEntity> postEntityList = userRepository.findByEmail(email).get().getPostEntities();
        List<PostDto> postDtoList = postEntityList.stream()
                .map(PostEntity::toDto)
                .collect(Collectors.toList());
        return postDtoList;
    }

    // like를 어떤 식으로...? 찜한 게시물을 보여주는 건데 그러면 게시물에
    public List<PostDto> getHeartPosts(String email){
        List<HeartDto> heartDtoList = heartRepository
                .findAll(userRepository.findByEmail(email).get().getUserId())
                .stream()
                .map(HeartEntity::toDto)
                .collect(Collectors.toList());

        List<PostDto> postDtoList = heartDtoList.stream()
                .map(HeartDto::getPostDto)
                .collect(Collectors.toList());
        return postDtoList;
    }

    public UserInfoDto updateUser(UserInfoDto userInfoDto,String email) throws BaseException{
        UserEntity userEntity = userRepository.findByEmail(email).get();
        if(!userEntity.isPresent()) {
            throw new BaseException(USERS_EMPTY_USER_EMAIL);
        }
        userEntity = updateUserUsingUserInfoDto(userEntity, userInfoDto);
        userRepository.save(userEntity);
        return userEntity.toDto();
    }

    private UserEntity updateUserUsingUserInfoDto(UserEntity userEntity, UserInfoDto userInfoDto){
        userEntity.setEmail(userInfoDto.getEmail());
        userEntity.setName(userInfoDto.getName());
        userEntity.setPassword(userInfoDto.getPassword());
        userEntity.setNickName(userInfoDto.getNickName());
        userEntity.setPhone(userInfoDto.getPhone());
        userEntity.setBirth(userInfoDto.getBirth());
        userEntity.setProfileImg(userInfoDto.getProfileImg());
        userEntity.setUniversity(userInfoDto.getUniversity());
        userEntity.setUserStatus(userInfoDto.getUserStatus());
        userEntity.setUserScore(userInfoDto.getUserScore());
        userEntity.setRole(userInfoDto.getRole());
        userEntity.setPostEntities(userInfoDto.toEntity().getPostEntities());
        return userEntity;
    }
}

/*
userEntity.setEmail(userInfoDto.getEmail());
        userEntity.setName(userInfoDto.getName());
        userEntity.setPassword(userInfoDto.getPassword());
        userEntity.setNickName(userInfoDto.getNickName());
        userEntity.setPhone(userInfoDto.getPhone());
        userEntity.setBirth(userInfoDto.getBirth());
        userEntity.setProfileImg(userInfoDto.getProfileImg());
        userEntity.setUniversity(userInfoDto.getUniversity());
        userEntity.setUserStatus(userInfoDto.getUserStatus());
        userEntity.setUserScore(userInfoDto.getUserScore());
        userEntity.setRole(userInfoDto.getRole());
        userEntity.setPostEntities(userInfoDto.toEntity().getPostEntities());*/
