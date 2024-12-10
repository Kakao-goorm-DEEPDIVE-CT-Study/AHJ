package com.example.exp7.controller;

import com.example.exp7.model.User;
import com.example.exp7.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(Model model){
        model.addAttribute("user",new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user")User user, Model model){
        String result = userService.loginUser(user.getId(),user.getPassword());
        model.addAttribute("message", result);
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("user",new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user")User user, Model model){
        String result = userService.registerUser(user.getId(), user.getPassword(), user.getEmail());
        model.addAttribute("message", result);
        if(result.equals("회원가입 성공")){
            model.addAttribute("user",new User());
            return "redirect:/login";
        }
        return "register";
    }
    @GetMapping("/find")
    public String find(){
        return "idpw_find";
    }
    @PostMapping("/find")
    public String find(@RequestParam("email")String email, Model model){
        User result = userService.findByEmail(email);
        if(result != null){
            model.addAttribute("message","사용자를 찾았습니다");
            model.addAttribute("user",result);
        } else{
            model.addAttribute("message","사용자를 찾지 못했습니다");
        }
        return "idpw_find";
    }

}
