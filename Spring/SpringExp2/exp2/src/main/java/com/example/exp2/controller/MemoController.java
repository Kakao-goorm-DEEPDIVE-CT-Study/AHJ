package com.example.exp2.controller;

import com.example.exp2.model.Memo;
import com.example.exp2.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MemoController {

    private final MemoService memoService;

    @Autowired
    public MemoController(MemoService memoService){
        this.memoService = memoService;
    }

    @GetMapping("/memo")
    public String showMemoList(Model model){
        model.addAttribute("memoList",memoService.showMemoList());
        System.out.println(memoService.showMemoList());
        return "memo-list";
    }

    @GetMapping("/show")
    public String showMemo(@RequestParam int choice, Model model){
        Memo memo = memoService.getMemo(choice);
        if(memo == null){
            model.addAttribute("error","메모가 없습니다");
        }else {
            model.addAttribute("memo",memo);
        }
        return "show-memo";
    }

    @GetMapping("/add")
    public String addMemo(){
        return "add-memo";
    }

    @PostMapping("/add")
    public String addMemo(@ModelAttribute("memo")Memo memo){
        memoService.addMemo(memo);
        return "redirect:/memo";
    }

    @GetMapping("/edit")
    public String editMemo(Model model, int index){
        model.addAttribute("memo",memoService.getMemo(index));
        model.addAttribute("index",index);
        return "edit-memo";
    }

    @PostMapping("/edit")
    public String editMemo(@ModelAttribute("memo")Memo memo, @RequestParam int index){
        memoService.editMemo(memo,index);
        return "redirect:/memo";
    }

    @PostMapping("/remove")
    public String removeMemo(@RequestParam int index){
        memoService.removeMemo(index);
        return "redirect:/memo";
    }
}
