package com.example.nanuer_server.service.mypage;

import com.example.nanuer_server.config.BaseException;
import com.example.nanuer_server.config.BaseResponseStatus;
import com.example.nanuer_server.domain.entity.UserEntity;
import com.example.nanuer_server.domain.repository.MyPageRepository;
import com.example.nanuer_server.domain.repository.UserRepository;
import com.example.nanuer_server.dto.mypage.MyPageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.nanuer_server.config.BaseResponseStatus.USERS_EMPTY_USER_ID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyPageService {
    private final UserRepository userRepository;

    private final MyPageRepository myPageRepository;

    public MyPageDto getMyPage(String id) throws BaseException{
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if(!userEntity.isPresent()){
            throw new BaseException(USERS_EMPTY_USER_ID);
        }
        MyPageDto myPageDto = userEntity.get().getMyPageEntity().toDto();
        return myPageDto;
    }
}
