package com.example.nanuer_server.service.User;

import com.example.nanuer_server.config.User.CustomUserDetails;
import com.example.nanuer_server.domain.entity.User;
import com.example.nanuer_server.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    //로그인할 때 들어온 username으로 DB에서 정보 찾기
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. : " + id));

        //UserDetailsImpl에서 정의한 생성자
        return new CustomUserDetails(userEntity);
    }
}