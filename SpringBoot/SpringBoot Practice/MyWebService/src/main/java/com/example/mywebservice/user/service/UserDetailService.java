package com.example.mywebservice.user.service;

import com.example.mywebservice.user.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {//스프링 시큐리티에서 사용자 정보를 가져오는 인터페이스
    //스프링 시큐리티가 내부적으로 사용하기에 개발자가 호출할 필요가 없음
    private final UserRepository userRepository;

    //사용자 이름으로 사용자의 정보를 가져오는 메서드
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("[loadUserByUsername] No user found with email : " + email));
    }
}
