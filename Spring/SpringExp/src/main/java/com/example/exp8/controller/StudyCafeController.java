package com.example.exp8.controller;
import com.example.exp8.model.User;
import com.example.exp8.service.MakeIdService;
import com.example.exp8.service.StudyCafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("user")
public class StudyCafeController {

    private final StudyCafeService studyCafeService;
    private final MakeIdService makeIdService;

    @ModelAttribute("user")
    public User createUser() {
        return new User();  // 새로운 User 객체를 반환
    }
    @Autowired
    public StudyCafeController(StudyCafeService studyCafeService, MakeIdService makeIdService){
        this.studyCafeService = studyCafeService;
        this.makeIdService = makeIdService;
    }

    @GetMapping("/studycafe")
    public String loginStudyCafe(){
        return "study-register";
    }

    @PostMapping("/studycafe")
    public String loginStudyCafeResult(@ModelAttribute("user")User user, Model model, HttpSession session){
        String id = makeIdService.makeId(user.getName(),user.getCode());
        session.setAttribute("id",id);
        model.addAttribute("id",id);
        return "service-list";
    }
    @PostMapping("/generate")
    public String generateId(@ModelAttribute("user")User user, Model model,HttpSession session){
        String id = makeIdService.makeId(user.getName(),user.getCode());
        session.setAttribute("id",id);
        model.addAttribute("id",id);
        return "service-list";
    }
    @GetMapping("/reserve")
    public String reserveSeat(){
        return "reserve";
    }
    @PostMapping("/reserve")
    public String reserveSeatResult(@RequestParam int seatNum,Model model, @ModelAttribute("user") User user){
        String result = studyCafeService.reserveSeat(seatNum, user);
        model.addAttribute("user",user);
        model.addAttribute("result",result);
        return "service-list";
    }
    @GetMapping("/use")
    public String useSeat(@ModelAttribute("user")User user,Model model){
        model.addAttribute("user", user);
        return "use";
    }
    @PostMapping("/use")
    public String useSeatResult(@ModelAttribute("user")User user,@RequestParam int choice, Model model){
        String result = studyCafeService.useSeat(user,user.getSeats().get(choice));
        model.addAttribute("result",result);
        return "service-list";
    }

    @PostMapping("/exit")
    public String exitSeat(@ModelAttribute("user")User user, Model model){
        String result = studyCafeService.deleteReserve(user);
        model.addAttribute("result",result);
        return "service-list";
    }
}
