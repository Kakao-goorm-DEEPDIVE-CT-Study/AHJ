package com.example.mywebservice.controller;

import com.example.mywebservice.controller.model.PostCreateRequest;
import com.example.mywebservice.entity.PostEntity;
import com.example.mywebservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    //게시글 작성
    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("post", PostCreateRequest.builder().build());
        return "create";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute("request") PostCreateRequest request){
        postService.createPosts(
                postC
        )
    }
    //게시글 목록 보기

    //게시글 상세 보기

    //게시글 수정하기

    //게시글 삭제하기
}
