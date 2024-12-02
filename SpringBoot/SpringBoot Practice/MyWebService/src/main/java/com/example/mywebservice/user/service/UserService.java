package com.example.mywebservice.user.service;

import com.example.mywebservice.user.controller.dto.AddUserDto;
import com.example.mywebservice.user.entity.UserEntity;
import com.example.mywebservice.user.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public Long save(AddUserDto dto){
        UserEntity userEntity = UserEntity.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();
        return userRepository.save(userEntity).getId();
    }
}
