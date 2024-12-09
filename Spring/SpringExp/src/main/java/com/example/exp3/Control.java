package com.example.exp3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

@Controller
public class Control {
    @Autowired
    ServiceAutoWired serviceAutoWired;
    @Inject
    ServiceInject serviceInject;

    @GetMapping("/test")
    @ResponseBody
    public String runService(){
        serviceAutoWired.log();
        serviceInject.log();
        return "OK";
    }
}
