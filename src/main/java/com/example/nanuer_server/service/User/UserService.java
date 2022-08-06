package com.example.nanuer_server.service.User;

import com.example.nanuer_server.config.BaseException;
import static com.example.nanuer_server.config.BaseResponseStatus.*;

import com.example.nanuer_server.domain.entity.PostEntity;
import com.example.nanuer_server.domain.entity.UserEntity;
import com.example.nanuer_server.domain.repository.PostRepository;
import com.example.nanuer_server.domain.repository.UserRepository;
import com.example.nanuer_server.dto.User.JoinUserDto;
import com.example.nanuer_server.dto.User.LoginUserDto;
import com.example.nanuer_server.dto.User.UserInfoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PostRepository postRepository;

    //회원가입

    public UserEntity signup(JoinUserDto userDto) throws BaseException {
        String email = userDto.getEmail();
        if (userRepository.findByEmail(userDto.getEmail()).orElse(null) != null) {
            throw new BaseException(POST_USERS_EXISTS_EMAIL);
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
    public UserInfoDto login(LoginUserDto loginUserDto) throws BaseException {
        UserEntity userEntity = userRepository.findByEmail(loginUserDto.getEmail()).orElseThrow(
                () -> new BaseException(FAILED_TO_LOGIN)
        );
        if (!passwordEncoder.matches(loginUserDto.getPassword(), userEntity.getPassword())) {
            throw new BaseException(FAILED_TO_LOGIN);
        }
        UserInfoDto userInfoDto = userEntity.toDto();
        return userInfoDto;
    }

    //삭제
    public void delete(String email) throws BaseException {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        if(!userEntity.isPresent()) {
            throw new BaseException(USERS_EMPTY_USER_EMAIL);
        }
        userEntity.get().getPostEntities().forEach(postEntity -> postEntity.delete());
        userRepository.delete(userEntity.get());
    }

    //비활성화, 활성화
    public void UserStatus(String email) throws BaseException {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        if(userEntity.isEmpty()) {
            throw new BaseException(USERS_EMPTY_USER_EMAIL);
        }
        else{
            if(Objects.equals(userEntity.get().getUserStatus(), "active")) {
                userEntity.get().status("inactive");
            }
            else if(Objects.equals(userEntity.get().getUserStatus(), "inactive")){
                userEntity.get().status("active");
            }
        }
    }

    //유저 정보 가져오기
    public UserInfoDto GetUser(String email) throws BaseException {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        UserInfoDto userInfoDto = userEntity.get().toDto();
        if(!userEntity.isPresent()) {
            throw new BaseException(USERS_EMPTY_USER_EMAIL);
        }
        return userInfoDto;
    }

    public UserInfoDto GetUserByPhone(String phone) throws BaseException {
        Optional<UserEntity> userEntity = userRepository.findByPhone(phone);
        UserInfoDto userInfoDto = userEntity.get().toDto();
        if(!userEntity.isPresent()) {
            throw new BaseException(USERS_EMPTY_USER_EMAIL);
        }
        return userInfoDto;
    }

}
