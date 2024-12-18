package com.example.mysql_connect.user.controller;

import com.example.mysql_connect.user.db.UserEntity;
import com.example.mysql_connect.user.db.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController {

    private final UserRepository userRepository;

    @GetMapping("/find-all")
    public List<UserEntity> findAll(){
        return userRepository.findAll();
    }

    @GetMapping("/name")
    public void autoSave(@RequestParam String name){
        UserEntity user = UserEntity.builder()
                .name(name)
                .build();
        userRepository.save(user);
    }

}
