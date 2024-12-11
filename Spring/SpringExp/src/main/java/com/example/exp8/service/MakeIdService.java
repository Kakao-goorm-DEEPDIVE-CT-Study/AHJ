package com.example.exp8.service;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@RequestScope
@Service
public class MakeIdService {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    public MakeIdService() {
        System.out.println("MakeIdService.MakeIdService");
        System.out.println("MakeIdService Request Scope Instance Created: " + this.hashCode());
    }
    public String makeId(String name, String code) {
        return name + LocalDateTime.now().format(formatter) + code;
    }
}
