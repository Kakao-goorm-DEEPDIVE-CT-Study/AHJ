package com.example.myboard.post.service;

import com.example.myboard.post.db.PostEntity;
import com.example.myboard.post.db.PostRepository;
import com.example.myboard.post.model.PostRequest;
import com.example.myboard.post.model.PostResponse;
import com.example.myboard.post.model.PostViewRequest;
import com.example.myboard.post.model.PostViewResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public PostResponse create(@Valid PostRequest postRequest){
        var entity = PostEntity.builder()
                .boardId(1L)
                .userName(postRequest.getUserName())
                .password(postRequest.getPassword())
                .email(postRequest.getEmail())
                .status("REGISTERED")
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .postedAt(LocalDateTime.now())
                .build();
        var saveEntity = postRepository.save(entity);

        return PostResponse.builder()
                .id(saveEntity.getId())
                .status(saveEntity.getStatus())
                .build();
    }

    public PostViewResponse view(@Valid PostViewRequest postViewRequest){
        var entity = postRepository.findFirstByIdAndStatusOrderByIdDesc(postViewRequest.getPostId(),"REGISTERED")
                .map( it->{
                    if(!it.getPassword().equals(postViewRequest.getPassword())){
//                        throw new RuntimeException("패스워드가 일치하지 않습니다.");
                        var format = "패스워드가 일치하지 않습니다. %s vs %s";
                        throw new RuntimeException(String.format(format,it.getPassword(), postViewRequest.getPassword()));
                    }
                    return it;
                })
                .orElseThrow(() -> new RuntimeException("해당 게시글이 존재하지 않습니다 : " + postViewRequest.getPostId()));
        return PostViewResponse.builder()
                .id(entity.getId())
                .userName(entity.getUserName())
                .email(entity.getEmail())
                .title(entity.getTitle())
                .content(entity.getContent())
                .postedAt(entity.getPostedAt())
                .build();
    }

    public List<PostViewResponse> all(){
       return postRepository.findAllByStatusOrderByIdDesc("REGISTERED").stream()
               .map(postEntity -> PostViewResponse
                       .builder()
                       .id(postEntity.getId())
                       .userName(postEntity.getUserName())
                       .email(postEntity.getEmail())
                       .title(postEntity.getTitle())
                       .content(postEntity.getContent())
                       .postedAt(postEntity.getPostedAt())
                       .build())
               .collect(Collectors.toList());
    }

    public void delete(@Valid PostViewRequest postViewRequest) {
        postRepository.findById(postViewRequest.getPostId())
                .map(it -> {
                    if(!it.getPassword().equals(postViewRequest.getPassword())){
                        throw new RuntimeException("패스워드가 일치하지 않습니다");
                    }
                    it.setStatus("UNREGISTERED");
                    postRepository.save(it);
                    return it;
                })
                .orElseThrow(() -> new RuntimeException("해당 게시글이 존재하지 않습니다 : " + postViewRequest.getPostId()));
    }
}
