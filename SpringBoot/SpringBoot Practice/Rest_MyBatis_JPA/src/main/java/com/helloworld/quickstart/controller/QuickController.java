package com.helloworld.quickstart.controller;

import com.helloworld.quickstart.dto.ItemDto;
import com.helloworld.quickstart.dto.ResponseDto;
import com.helloworld.quickstart.service.QuickService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class QuickController {

    @Autowired
    QuickService quickService;

    @GetMapping("/dummy")
    public String dummy(){
        log.info("dummy");
        return "{}";
    }

    @GetMapping("/dummy2")
    public String dummy2(){
        log.info("dummy");
        return "dummy2";
    }

    @GetMapping("/member")
    public String getMember(@RequestParam("empNo") String empNo
    , @RequestParam("year") int year){
        log.info("empNo : {}",empNo);
        log.info("year : {}", year);
        return "ok";
    }

    @GetMapping("/company/{id}")
    public String getCompany(@RequestParam("id") String id){
        log.info("id : {}", id);
        return "ok";
    }

    @PostMapping("/item")
    public ResponseDto registerItem(@RequestBody ItemDto item){
        log.info("item : {} ",item);
        boolean result = quickService.registerItem(item);
        if(result){
            ResponseDto responseDto = new ResponseDto();
            responseDto.setMessage("ok");
            return responseDto;
        } else {
            ResponseDto responseDto = new ResponseDto();
            responseDto.setMessage("fail");
            return responseDto;
        }
    }

    @GetMapping("/item")
    public ItemDto getItem(@RequestParam("id") String id){
        return quickService.getItemById(id);
    }
}
