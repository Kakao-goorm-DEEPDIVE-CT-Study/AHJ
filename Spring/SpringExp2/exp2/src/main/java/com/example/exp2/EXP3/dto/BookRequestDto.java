package com.example.exp2.EXP3.dto;

import lombok.Data;

@Data
public class BookRequestDto {
    private String title;
    private String author;
    private String publisher;
    private int price;
}
