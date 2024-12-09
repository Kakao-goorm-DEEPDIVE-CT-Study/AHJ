package com.example.exp4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("instaService")
public class InstaService implements SNSService{

    @Override
    public String sendSNS(String message) {
        return "Insta Message Send : " + message;
    }
}
