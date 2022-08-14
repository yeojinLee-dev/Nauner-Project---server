package com.example.nanuer_server.controller;

import com.example.nanuer_server.config.BaseException;
import com.example.nanuer_server.config.BaseResponse;
import com.example.nanuer_server.config.User.JwtTokenProvider;
import com.example.nanuer_server.domain.entity.UserEntity;
import com.example.nanuer_server.domain.entity.UserRole;
import com.example.nanuer_server.dto.User.JoinUserDto;
import com.example.nanuer_server.dto.User.LoginUserDto;
import com.example.nanuer_server.dto.User.UserInfoDto;
import com.example.nanuer_server.service.User.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequiredArgsConstructor
@RestController
public class PermitController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @ResponseBody
    @PostMapping("/join")
    public BaseResponse<UserEntity> join(@RequestBody JoinUserDto joinUserDto) {
        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //UserEntity userEntity = UserEntity.createUser(userDto);
        try {
            UserEntity userEntity = userService.signup(joinUserDto);
            return new BaseResponse<>(userEntity);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //로그인

    @PostMapping("/login")
    @ResponseBody
    public BaseResponse<String> login(@RequestBody LoginUserDto loginUserDto) {
        try {

            UserInfoDto userInfoDto = userService.login(loginUserDto);
            String Email = userInfoDto.getEmail();
            UserRole role = userInfoDto.getRole();
            String result = jwtTokenProvider.createToken(Email, role);
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }


}
