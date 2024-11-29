package com.example.restapi_ex.transaction.controller;

import com.example.restapi_ex.transaction.controller.model.UserRequest;
import com.example.restapi_ex.transaction.controller.model.UserResponse;
import com.example.restapi_ex.transaction.entity.UserEntity;
import com.example.restapi_ex.transaction.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    //생성
    @PostMapping
    public UserResponse create(@RequestBody UserRequest userRequest){
        UserEntity entity = userService.createUser(
                UserEntity.builder()
                        .name(userRequest.getName())
                        .email(userRequest.getEmail())
                        .build()
        );
        return UserResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .build();
    }
    @PostMapping("/rollback")
    public UserResponse createRollBack(@RequestBody UserRequest userRequest) throws Exception{
        UserEntity entity = userService.createUserWithRollBack(
                UserEntity.builder()
                        .name(userRequest.getName())
                        .email(userRequest.getEmail())
                        .build()
        );
        return UserResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .build();
    }
    //조회
    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id){
        UserEntity entity = userService.getUserById(id);
        if(entity == null){
            return null;
        }
        return UserResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .build();
    }
    //업데이트
    @PutMapping("/{id}")
    public UserResponse updateUserById(@PathVariable Long id, @RequestBody UserRequest request){
        UserEntity userEntity = userService.updateUser(id,
                UserEntity.builder()
                        .name(request.getName())
                        .email(request.getEmail())
                        .build()
        );
        return UserResponse.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .build();
    }
}
