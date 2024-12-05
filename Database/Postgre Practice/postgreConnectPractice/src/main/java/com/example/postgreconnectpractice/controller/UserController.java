package com.example.postgreconnectpractice.controller;

import com.example.postgreconnectpractice.entity.User;
import com.example.postgreconnectpractice.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // 모든 사용자 조회
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 사용자 및 관련 Post 저장
    @PostMapping
    public User createUser(@RequestBody User user) {
        // CascadeType.ALL이 설정되어 있어야 Post도 저장됩니다.
        return userRepository.save(user);
    }
}
