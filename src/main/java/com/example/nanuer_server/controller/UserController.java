package com.example.nanuer_server.controller;

import com.example.nanuer_server.domain.entity.UserEntity;
import com.example.nanuer_server.dto.UserDto;

import com.example.nanuer_server.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


@Log4j2
@RequestMapping("/user")
@RequiredArgsConstructor
@RestController
public class UserController {

    //private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("")
    public String login() {
        return "login";
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
}