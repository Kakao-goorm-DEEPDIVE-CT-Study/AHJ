package com.example.restapi_ex.hibernate.controller;

import com.example.restapi_ex.hibernate.controller.model.ItemHibernateDto;
import com.example.restapi_ex.hibernate.controller.model.ResponseHibernateDto;
import com.example.restapi_ex.hibernate.service.HibernateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HibernateController {

    private final HibernateService hibernateService;

    @PostMapping("/hibernate")
    public ResponseHibernateDto HibernateTest(@RequestBody ItemHibernateDto itemHibernateDto){
        log.info("item : " + itemHibernateDto);
        boolean b = hibernateService.registerHibernate(itemHibernateDto);
        if(b){
            ResponseHibernateDto responseHibernateDto = new ResponseHibernateDto();
            responseHibernateDto.setMessage("OK");
            return responseHibernateDto;
        } else{
            ResponseHibernateDto responseHibernateDto = new ResponseHibernateDto();
            responseHibernateDto.setMessage("ERROR");
            return responseHibernateDto;
        }
    }
}
