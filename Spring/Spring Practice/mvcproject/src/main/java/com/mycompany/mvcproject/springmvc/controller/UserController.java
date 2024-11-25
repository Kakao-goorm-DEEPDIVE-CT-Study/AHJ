package com.mycompany.mvcproject.springmvc.controller;

import com.mycompany.mvcproject.login.domain.User;
import com.mycompany.mvcproject.springmvc.domain.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/userMVC")
public class UserController {

    @GetMapping("/signupMVC")
    public String signup(Model model){
        UserEntity userEntity = new UserEntity();
        model.addAttribute("user",userEntity);
        return "signup";
    }

    @PostMapping(value = "signupMVC")
//    @ResponseBody
    //@ModelAttribute User user "user"와 user가 같을때는 가능
    public String signup(@ModelAttribute("user") UserEntity user){//위와 같은 방식
        //Model model은 다음페이지로 넘겨주기 위함
        //넘겨주려면 model.addAttribute로 데이터를 다시 담아줘야함
        System.out.println(user.toString());
        return "redirect:/hello";
    }
}
