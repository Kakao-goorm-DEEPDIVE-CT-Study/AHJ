package com.example.exp3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ServiceInject {
    public void log(){
        log.info("ServiceInject");
    }
}
