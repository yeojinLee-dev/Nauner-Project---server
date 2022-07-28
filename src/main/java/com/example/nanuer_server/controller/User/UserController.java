package com.example.nanuer_server.controller.User;

import com.example.nanuer_server.config.BaseException;
import com.example.nanuer_server.config.BaseResponse;
import com.example.nanuer_server.domain.entity.User;
import com.example.nanuer_server.domain.entity.UserRole;
import com.example.nanuer_server.dto.User.UserDto;

import com.example.nanuer_server.service.User.UserService;
import com.example.nanuer_server.config.User.JwtTokenProvider;
import com.example.nanuer_server.dto.User.LoginUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;


@Log4j2
@RequestMapping("/user")
@RequiredArgsConstructor
@RestController
public class UserController {


    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("")
    public String hello() {
        return "hello";
    }


    @ResponseBody
    @PostMapping("/join")
    public BaseResponse<User> join(@RequestBody UserDto userDto) throws BaseException {
        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordzEncoder();
        //UserEntity userEntity = UserEntity.createUser(userDto);
        log.info("userId={}", userDto.getId());
        log.info("password={}", userDto.getPassword());
        log.info("email={}", userDto.getEmail());
        try {
            User userEntity = userService.signup(userDto);
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
            User userEntity = userService.login(loginUserDto);
            String Id = userEntity.getId();
            UserRole role = userEntity.getRole();
            String result = jwtTokenProvider.createToken(Id, role);
            return new BaseResponse<>(result);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    //삭제
    @DeleteMapping("/{id}")
    public BaseResponse<String> delete(@PathVariable("id") String id) {
        try{
            userService.delete(id);
            String result = id + " 해당 유저가 삭제되었습니다.";
            return new BaseResponse<>(result);

        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //유저 상태관리
    @PatchMapping("/{id}/status")
    public BaseResponse<String> UserStatus(@PathVariable("id") String id) {
        try{
            userService.UserStatus(id);
            User userEntity = userService.GetUser(id);
            String status = userEntity.getUserStatus();
            String result = id + " 해당 유저가 " + status + " 되었습니다.";
            return new BaseResponse<>(result);

        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    //유저 정보 조회
    @GetMapping("/{id}")
    public BaseResponse<User> GetUser(@PathVariable("id") String id) {
        try{
            User userInfo = userService.GetUser(id);

            return new BaseResponse<>(userInfo);

        }
        catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }



}