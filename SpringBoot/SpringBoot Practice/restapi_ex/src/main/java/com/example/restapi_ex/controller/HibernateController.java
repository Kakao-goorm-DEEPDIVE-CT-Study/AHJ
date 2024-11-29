package com.example.restapi_ex.controller;

import com.example.restapi_ex.controller.model.ItemDto;
import com.example.restapi_ex.controller.model.ResponseDto;
import com.example.restapi_ex.service.HibernateService;
import com.example.restapi_ex.service.RestExService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HibernateController {

    private final HibernateService hibernateService;

    @PostMapping("/hibernate")
    public ResponseDto HibernateTest(@RequestBody ItemDto itemDto){
        log.info("item : " + itemDto);
        boolean b = hibernateService.registerHibernate(itemDto);
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
