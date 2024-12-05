package com.example.postgreconnectpractice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    // User와의 다대일 관계 (N:1)
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
