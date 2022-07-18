package com.example.nanuer_server.service.User;

import com.example.nanuer_server.config.BaseException;
import static com.example.nanuer_server.config.BaseResponseStatus.*;
import com.example.nanuer_server.domain.entity.UserEntity;
import com.example.nanuer_server.domain.repository.UserRepository;
import com.example.nanuer_server.dto.User.UserDto;
import com.example.nanuer_server.dto.User.LoginUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //회원가입

    public UserEntity signup(UserDto userDto) throws BaseException {
        String id = userDto.getId();
        if (userRepository.findById(userDto.getId()).orElse(null) != null) {
            throw new BaseException(POST_USERS_EXISTS_ID);
        }
        try{
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            return userRepository.save(userDto.toEntity());
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    //로그인
    public UserEntity login(LoginUserDto loginUserDto) throws BaseException {
        UserEntity userEntity = userRepository.findById(loginUserDto.getId()).orElseThrow(
                () -> new BaseException(FAILED_TO_LOGIN)
        );
        if (!passwordEncoder.matches(loginUserDto.getPassword(), userEntity.getPassword())) {
            throw new BaseException(FAILED_TO_LOGIN);
        }
        return userEntity;
    }

    //삭제
    public void delete(String id) throws BaseException {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if(!userEntity.isPresent()) {
            throw new BaseException(USERS_EMPTY_USER_ID);
        }
        userRepository.delete(userEntity.get());

    }


}
