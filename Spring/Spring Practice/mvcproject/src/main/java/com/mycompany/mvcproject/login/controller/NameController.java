package com.mycompany.mvcproject.login.controller;

import com.mycompany.mvcproject.login.service.LoggedUserManagementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NameController {

    private final LoggedUserManagementService loggedUserManagementService;

    public NameController(LoggedUserManagementService loggedUserManagementService) {
        this.loggedUserManagementService = loggedUserManagementService;
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false) String logout, Model model){
        if(logout != null){//로그 아웃
            loggedUserManagementService.setUserName(null);
            return "redirect:/user/login";
        }
        String userName = loggedUserManagementService.getUserName();
        if(userName == null){//비정상 상태 처리
            return "redirect:/user/login";
        }
        model.addAttribute("userName",userName);
        return "main";
    }
}
