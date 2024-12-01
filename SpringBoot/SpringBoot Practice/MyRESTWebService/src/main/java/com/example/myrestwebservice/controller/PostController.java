package com.example.myrestwebservice.controller;

import com.example.myrestwebservice.controller.model.PostRequest;
import com.example.myrestwebservice.controller.model.PostResponse;
import com.example.myrestwebservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    //게시글 작성
    @PostMapping
    public PostResponse createPost(@RequestBody PostRequest request){
        return postService.createPosts(request);
    }

    //게시글 목록 보기
    @GetMapping
    public List<PostResponse> list(){
        return postService.getAllPosts();
    }

    //게시글 상세 보기
    @GetMapping("/{id}")
    public PostResponse detail(@PathVariable Long id){
        return postService.getPostById(id).orElseThrow(() -> new RuntimeException("Post not found"));
    }

    //게시글 수정하기
    @PutMapping("/{id}")
    public PostResponse editPost(@PathVariable Long id, @RequestBody PostRequest request){
        return postService.updatePost(id,request);
    }

    //게시글 삭제하기
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        postService.deletePost(id);
    }
}
