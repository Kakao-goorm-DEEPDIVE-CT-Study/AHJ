package com.example.exp8.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
@Data
public class User {
    private String name;
    private String code;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endDate;
    private ArrayList<Integer> seats = new ArrayList<>();
    private int currentSeat;
}
