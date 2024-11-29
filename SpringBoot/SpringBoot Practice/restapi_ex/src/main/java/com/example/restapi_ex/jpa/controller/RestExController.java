package com.example.restapi_ex.jpa.controller;

import com.example.restapi_ex.jpa.controller.model.ItemDto;
import com.example.restapi_ex.jpa.controller.model.ResponseDto;
import com.example.restapi_ex.jpa.controller.service.RestExService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RestExController {

    private final RestExService restExService;

    @GetMapping("test")
    public String test(){
        log.info("test");
        return "{}";
    }

    @GetMapping("test2")
    public String test2(){
        log.info("test2");
        return "test2";
    }

    @PostMapping("/item")
    public ResponseDto testRequestBody(@RequestBody ItemDto itemDto){
        log.info("item : " + itemDto);
        boolean b = restExService.registerItem(itemDto);
        if(b){
            ResponseDto responseDto = new ResponseDto();
            responseDto.setMessage("OK");
            return responseDto;
        } else{
            ResponseDto responseDto = new ResponseDto();
            responseDto.setMessage("ERROR");
            return responseDto;
        }
    }
}
