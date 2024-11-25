package com.mycompany.mvcproject.email.controller;

import com.mycompany.mvcproject.email.domain.SendEmailRequest;
import com.mycompany.mvcproject.email.service.EmailServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/email")
public class EmailController {
    //1. 필드 주입
   /* @Autowired
    EmailServiceClient emailServiceClient;*/

    //2. 생성자 주입
    private final EmailServiceClient emailServiceClient;

    @Autowired
    public EmailController(EmailServiceClient emailServiceClient){
        this.emailServiceClient = emailServiceClient;
    }

    //3. 세터 주입
    /*EmailServiceClient emailServiceClient;

    public void setEmailServiceClient(EmailServiceClient emailServiceClient){
        this.emailServiceClient = emailServiceClient;
    }*/


    @RequestMapping("/sendEmail")
    @ResponseBody
    public String sendEmail(){
//        EmailServiceClient emailServiceClient = new EmailServiceClient();//객체생성은 sendEmail의 관심사는 아님
        SendEmailRequest sendEmailRequest = generatedEmailRequest();
        emailServiceClient.sendEmail(sendEmailRequest);
        return "Success";
    }

    public SendEmailRequest generatedEmailRequest(){
        SendEmailRequest request = new SendEmailRequest();
        request.setToAddress("test@example.com");
        request.setSubject("test subject");
        request.setBody("This is a test email .....");
        return request;
    }
}

/*
* 1. Controller에서 요청을 받음
* 2. service 객체 생성
* 3. domain 객체 생성
* 4. service 메서드 호출
*   a. 서비스 로직
*/