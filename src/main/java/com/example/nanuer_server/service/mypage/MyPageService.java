package com.example.nanuer_server.service.mypage;

import com.example.nanuer_server.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyPageService {
    private final UserRepository userRepository;

   // private final MyPageRepository myPageRepository;

    /*
    public MyPageDto getMyPage(String email) throws BaseException{
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        if(!userEntity.isPresent()){
            throw new BaseException(USERS_EMPTY_USER_EMAIL);
        }
        MyPageDto myPageDto = userEntity.get().getMyPageEntity().toDto();
        return myPageDto;
    }*/
}
