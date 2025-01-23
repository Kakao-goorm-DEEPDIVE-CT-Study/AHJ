package io.goorm.mybatis.api.board.controller;

import io.goorm.mybatis.api.board.service.BoardService;
import io.goorm.mybatis.board.model.Board;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("")
    public String index(){
        return "board/index";
    }

    @GetMapping("/boards")
    public String list(Model model){
        model.addAttribute("posts", boardService.getBoards());
        return "board/list";
    }

    //상세 페이지
    @GetMapping("/boards/{seq}")
    public String detail(@PathVariable Long seq, Model model){
        model.addAttribute("post", boardService.getBoardByID(seq));
        return "board/view";
    }

    //등록페이지
    @GetMapping("/boards/create")
    public String create(){
        return "board/create";
    }

    //등록
    @PostMapping("/boards")
    public String post(@ModelAttribute Board board){
        boardService.createBoard(board);
        return "redirect:/board/boards";
    }

    //수정페이지
    @GetMapping("/boards/update/{seq}")
    public String update(@PathVariable Long seq, Model model){
        model.addAttribute("post", boardService.getBoardByID(seq));
        return "board/edit";
    }

    @PutMapping("/boards")
    public String put(@RequestBody Board board){
        boardService.updateBoard(board);
        return "redirect:/list";
    }
}
