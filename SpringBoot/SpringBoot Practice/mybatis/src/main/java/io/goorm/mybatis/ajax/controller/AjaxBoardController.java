package io.goorm.mybatis.ajax.controller;

import io.goorm.mybatis.api.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class AjaxBoardController {
    @Autowired
    BoardService boardService;

    @GetMapping("/ajax")
    public String index(){
        return "ajax/index";
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("posts", boardService.getBoards());
        return "ajax/list";
    }

    //상세 페이지
    @GetMapping("/view")
    public String detail(@RequestParam("seq") Long seq, Model model){
        model.addAttribute("post", boardService.getBoardByID(seq));
        return "ajax/detail";
    }

    //등록페이지
    @GetMapping("/write")
    public String create(){
        return "ajax/save";
    }

    //수정페이지
    @GetMapping("/update")
    public String update(@RequestParam("seq") Long seq, Model model){
        model.addAttribute("post", boardService.getBoardByID(seq));
        return "ajax/update";
    }
}
