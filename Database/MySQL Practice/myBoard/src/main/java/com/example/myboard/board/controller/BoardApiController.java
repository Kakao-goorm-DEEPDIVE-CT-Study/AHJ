package com.example.myboard.board.controller;

import com.example.myboard.board.model.BoardRequest;
import com.example.myboard.board.model.BoardResponse;
import com.example.myboard.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController {
    private final BoardService boardService;

    @PostMapping
    public BoardResponse create(@Valid @RequestBody BoardRequest boardRequest){
        return boardService.create(boardRequest);
    }
}
