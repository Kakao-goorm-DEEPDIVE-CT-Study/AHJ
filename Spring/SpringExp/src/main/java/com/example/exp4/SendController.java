package com.example.exp4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SendController {
    @Autowired
    @Qualifier("kakaoService")
    SNSService kakaoService;

    @Autowired
    @Qualifier("instaService")
    SNSService instaService;

    @GetMapping("/send-kakao")
    @ResponseBody
    public String sendKakao(@RequestParam String message){
        return kakaoService.sendSNS(message);
    }

    @GetMapping("/send-insta")
    @ResponseBody
    public String sendInsta(@RequestParam String message){
        return instaService.sendSNS(message);
    }
}
