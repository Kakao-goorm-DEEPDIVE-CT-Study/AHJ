package com.example.mywebservice.post.controller;

import com.example.mywebservice.post.controller.model.PostRequest;
import com.example.mywebservice.post.controller.model.PostResponse;
import com.example.mywebservice.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    //게시글 작성
    @GetMapping("/create")
    public String createForm(Model model){
        model.addAttribute("post", PostRequest.builder().build());
        return "create";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute("request") PostRequest request){
        postService.createPosts(request);
        return "redirect:/posts";
    }

    //게시글 목록 보기
    @GetMapping
    public String list(Model model){
        List<PostResponse> posts = postService.getAllPosts();
        model.addAttribute("posts",posts);
        return "lists";
    }

    //게시글 상세 보기
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model){
        PostResponse response = postService.getPostById(id).orElseThrow(() -> new RuntimeException("Post not found"));
        model.addAttribute("post",response);
        return "detail";
    }

    //게시글 수정하기
    @GetMapping("/{id}/edit")
    public String editPost(@PathVariable Long id, Model model){
        PostResponse response = postService.getPostById(id).orElseThrow(() -> new RuntimeException("Page Not Found"));
        model.addAttribute("post", response);
        return "edit";
    }
    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Long id, @ModelAttribute PostRequest request){
        postService.updatePost(id, request);
        return "redirect:/posts";
    }

    //게시글 삭제하기
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id){
        postService.deletePost(id);
        return "redirect:/posts";
    }
}
