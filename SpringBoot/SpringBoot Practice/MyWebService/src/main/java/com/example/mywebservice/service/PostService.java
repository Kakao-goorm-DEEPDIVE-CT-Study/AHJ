package com.example.mywebservice.service;

import com.example.mywebservice.controller.model.PostCreateRequest;
import com.example.mywebservice.controller.model.PostResponse;
import com.example.mywebservice.entity.PostEntity;
import com.example.mywebservice.entity.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    //작성하기
    public PostResponse createPosts(PostCreateRequest postCreateRequest){
        return entityToResponse(    postRepository.save(createRequestToEntity(postCreateRequest)));
    }
    //목록보기
    public List<PostEntity> getAllPosts(){
        return postRepository.findAll();
    }
    //상세보기
    public Optional<PostEntity> getPostById(Long id){
        return postRepository.findById(id);
    }
    //수정하기
    public PostEntity updatePost(Long id, PostEntity postEntity){
        return postRepository.findById(id)
                .map(post -> {
                    post.setTitle(postEntity.getTitle());
                    post.setContent(postEntity.getContent());
                    post.setAuthor(postEntity.getAuthor());
                    return postRepository.save(post);
                })
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }
    //삭제하기
    public void deletePost(Long id){
        postRepository.deleteById(id);
    }

    //
    private PostEntity createRequestToEntity(PostCreateRequest postCreateRequest){
        PostEntity postEntity = new PostEntity(
                postCreateRequest.getTitle(),
                postCreateRequest.getContent(),
                postCreateRequest.getAuthor()
        );
        return postEntity;
    }

    private PostResponse entityToResponse(PostEntity postEntity){
        return PostResponse.builder()
                .id(postEntity.getId())
                .title(postEntity.getTitle())
                .content(postEntity.getContent())
                .author(postEntity.getAuthor())
                .build();
    }
}
