package com.mycompany.mvcproject.login.controller;

import com.mycompany.mvcproject.login.domain.User;
import com.mycompany.mvcproject.login.service.LoggedUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserLoginController {

    @GetMapping("/login")
    public String login(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model){
        System.out.println("user email : " + user.getEmail());
        System.out.println("user password : " + user.getPassword());

        if(User.DEFAULT_EMAIL.equals(user.getEmail()) && User.DEFAULT_PASSWORD.equals(user.getPassword())){
            model.addAttribute("message", "You are now logged in.");
            return "loginSuccess";
        }else {
            return "redirect:/user/login";
        }
    }

    @GetMapping("/loginScope")
    public String loginScope(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "loginScope";
    }
    @PostMapping("/loginScope")
    public String loginScope(
            //RequestParam으로 가져오면 HTTP자격증명을 가져옴

            @RequestParam String email, @RequestParam String password, Model model
    ){
        System.out.println("email = " + email);
        System.out.println("password = " + password);

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        boolean result = user.login();
        if(result){
            model.addAttribute("message", "로그인 성공");
        } else{
            model.addAttribute("message", "로그인 실패");
        }
        return "loginScope";
    }
    @GetMapping("/loginSession")
    public String loginSession(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "loginSession";
    }

    @Autowired
    private LoggedUserManagementService loggedUserManagementService;

    @PostMapping("/loginSession")
    public String loginSession(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String name,
            Model model
    ){


        System.out.println("email = " + email);
        System.out.println("password = " + password);

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);

        boolean result = user.login();
        if(result){
            loggedUserManagementService.setUserName(name);
            model.addAttribute("userName",name);
            return "main";
        }
        model.addAttribute("message", "로그인 실패");
        return "loginSession";
    }
}
