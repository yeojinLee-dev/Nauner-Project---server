package com.example.nanuer_server.service;

import com.example.nanuer_server.domain.entity.UserEntity;
import com.example.nanuer_server.domain.repository.UserRepository;
import com.example.nanuer_server.dto.UserDto;
import com.example.nanuer_server.dto.UserSessionDto;
import com.example.nanuer_server.service.userSecurity.CustomUserDetails;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PatchMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@AllArgsConstructor
@Transactional
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    //private final BCryptPasswordEncoder encoder;
    private final HttpSession session;

    //회원가입
    public UserEntity saveUser(UserDto userDto){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        return userRepository.save(userDto.toEntity());
    }

    //로그인
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        UserEntity user = userRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. : " + id));

        session.setAttribute("user", new UserSessionDto(user));

        /* 시큐리티 세션에 유저 정보 저장 */
        return new CustomUserDetails(user);
    }

    //삭제1
    @Transactional
    public int delete(String id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if(userEntity.isPresent()) {
            userRepository.delete(userEntity.get());
            return 1;
        }
        return 0;
    }


}
