package com.example.nanuer_server.controller;

import com.example.nanuer_server.domain.entity.UserEntity;
import com.example.nanuer_server.dto.UserDto;

import com.example.nanuer_server.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Log4j2
@RequestMapping("/user")
@RequiredArgsConstructor
@RestController
public class UserController {

    //private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("")
    public String hello() {
        return "hello";
    }


    @PostMapping("/join")
    public UserEntity join(@RequestBody UserDto userDto) {
        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //UserEntity userEntity = UserEntity.createUser(userDto);
        log.info("userId={}", userDto.getId());
        log.info("password={}", userDto.getPassword());
        log.info("email={}",userDto.getEmail());
        return userService.saveUser(userDto);
    }

    //로그인

    @PostMapping("/user/login")
    public String login(Model model){
        model.addAttribute("user",new UserDto());
        return "login";
    }

    //삭제

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String id) {
        String response = "";
        if(userService.delete(id) ==0){
            response = id + " 삭제 실패하였습니다.";
        }
        else{
            response = id + " 삭제 성공하였습니다.";
        }

        return response;

    }

}