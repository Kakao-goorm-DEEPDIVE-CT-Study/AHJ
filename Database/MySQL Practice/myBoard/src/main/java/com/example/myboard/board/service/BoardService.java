package com.example.myboard.board.service;

import com.example.myboard.board.db.BoardEntity;
import com.example.myboard.board.db.BoardRepository;
import com.example.myboard.board.model.BoardRequest;
import com.example.myboard.board.model.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardResponse create(BoardRequest boardRequest) {
        var entity = BoardEntity.builder()
                .boardName(boardRequest.getBoardName())
                .status("REGISTERED")
                .build();
        var saveEntity = boardRepository.save(entity);

        return BoardResponse.builder()
                .boardId(saveEntity.getId())
                .status(saveEntity.getStatus())
                .build();
    }
}
