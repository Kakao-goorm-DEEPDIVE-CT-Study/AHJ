package com.example.exp4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service("kakaoService")
public class KakaoService implements SNSService{

    @Override
    public String sendSNS(String message) {
        return "Kakao Message Send : " + message;
    }
}
