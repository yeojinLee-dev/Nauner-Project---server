package com.example.nanuer_server.service.mypage;

import com.example.nanuer_server.config.BaseException;
import com.example.nanuer_server.config.BaseResponseStatus;
import com.example.nanuer_server.domain.entity.MyPageEntity;
import com.example.nanuer_server.domain.entity.PostEntity;
import com.example.nanuer_server.domain.entity.UserEntity;
import com.example.nanuer_server.domain.repository.MyPageRepository;
import com.example.nanuer_server.domain.repository.PostRepository;
import com.example.nanuer_server.domain.repository.UserRepository;
import com.example.nanuer_server.dto.mypage.MyPageDto;
import com.example.nanuer_server.dto.post.PostDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.nanuer_server.config.BaseResponseStatus.USERS_EMPTY_USER_ID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyPageService {
    private final UserRepository userRepository;

    private final MyPageRepository myPageRepository;

    private final PostRepository postRepository;

    public MyPageDto getMyPage(String id) throws BaseException{
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if(!userEntity.isPresent()){
            throw new BaseException(USERS_EMPTY_USER_ID);
        }
        MyPageDto myPageDto = userEntity.get().getMyPageEntity().toDto();
        return myPageDto;
    }

    //  자신이 작성한 게시물을 리스트 형식으로 리턴해주는 메서드
    public List<PostDto> getMyPagePosts(MyPageDto myPageDto){
        List<PostEntity> postEntities = postRepository.findAllByMyPageEntity(myPageDto.toEntity());
        return postEntities.stream()
                .map(PostEntity::toDto)
                .collect(Collectors.toList());
    }
}
