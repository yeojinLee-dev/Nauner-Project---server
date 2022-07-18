package com.example.nanuer_server.service.userSecurity;

import com.example.nanuer_server.domain.entity.UserEntity;
import com.example.nanuer_server.domain.repository.UserRepository;
import com.example.nanuer_server.dto.UserSessionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private final HttpSession session;

    /* id가 DB에 있는지 확인 */
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        UserEntity user = userRepository.findById(id).orElseThrow(() ->
                new UsernameNotFoundException("해당 사용자가 존재하지 않습니다. : " + id));

        session.setAttribute("user", new UserSessionDto(user));

        /* 시큐리티 세션에 유저 정보 저장 */
        return new CustomUserDetails(user);
    }

}