package com.mycompany.mvcproject.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class HelloController {

    @RequestMapping("/hello")
//    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String home(){
        return "hello";
    }

    @RequestMapping(value = "/requestMappingGetTest", method = RequestMethod.GET)
    public String requestMappingGetTest(Model model){
        model.addAttribute("textFromController","안녕하세요");
        return "requestHello";
    }

    @RequestMapping("/requestParamTest")
    public String requestParamTest(
            // default : required = true
            @RequestParam(name="a", required = false, defaultValue = "0") int a,
            @RequestParam("b") String b,
            @RequestParam(name = "c", defaultValue = "true") boolean c

    ){
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);
        return "hello";
    }

    //http://localhost:8080/requestParamMapTest?param1=value&param2=value2&aaa=777
    //불특정 다수의 파라미터 받을때 Map사용
    @GetMapping("/requestParamMapTest")
    public String requestParamMapTest(
            @RequestParam Map<String,String> map
    ){
        for(Map.Entry<String,String> entry : map.entrySet()){
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        return "hello";
    }

    // http://localhost:8080/pathVariableTest/spring/test/2024
    @GetMapping("/pathVariableTest/{a1}/{b}/{c}")
    public String pathVariableTest(
            @PathVariable(value = "a1") String a,
            @PathVariable String b,
            @PathVariable int c
    ){
        System.out.println("a = " + a);
        System.out.println("b = " + b);
        System.out.println("c = " + c);
        return "hello";
    }
}

