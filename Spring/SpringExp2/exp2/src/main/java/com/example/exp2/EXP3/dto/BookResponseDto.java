package com.example.exp2.EXP3.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BookResponseDto {
    private String title;
    private String author;
    private String publisher;
    private int price;
}
