package com.example.exp5.controller;

import com.example.exp5.model.Todo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/todo")
public class TodoController {

    private final List<Todo> todoList = new ArrayList<>();

    @GetMapping
    public String getTodoList(Model  model){
        model.addAttribute("todos",todoList);
        System.out.println("TodoController.getTodoList");
        return "todo-list";
    }

    @GetMapping("/add")
    public String addTodo(Model model){
        model.addAttribute("todo", new Todo());
        System.out.println("TodoController.addTodo : GET$");
        return "todo-add";
    }

    @PostMapping("/add")
    public String addTodo(@ModelAttribute("todo") Todo todo){
        todoList.add(todo);
        System.out.println("TodoController.addTodo : POST");
        return "redirect:/todo";
    }

}
