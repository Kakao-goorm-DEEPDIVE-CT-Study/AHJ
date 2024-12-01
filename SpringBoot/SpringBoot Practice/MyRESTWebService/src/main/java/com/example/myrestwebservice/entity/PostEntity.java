package com.example.myrestwebservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data

@Entity
@Table(name = "posts")
@Getter
@Setter
@NoArgsConstructor
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createAt;

    @PrePersist
    protected void onCreate(){
        this.createAt = LocalDateTime.now();
    }

    public PostEntity(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
