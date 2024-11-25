package com.mycompany.mvcproject.email.service;

import com.mycompany.mvcproject.email.domain.SendEmailRequest;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceClient {
    public void sendEmail(SendEmailRequest request){
        //실제 이메일 발송 로직 위치
        System.out.println("Sending email to : " + request.getToAddress());
        System.out.println("Subject : " + request.getSubject());
        System.out.println("Body : " + request.getBody());
        //이메일 발송 성공 시 추가적인 로직 작성

    }
}
