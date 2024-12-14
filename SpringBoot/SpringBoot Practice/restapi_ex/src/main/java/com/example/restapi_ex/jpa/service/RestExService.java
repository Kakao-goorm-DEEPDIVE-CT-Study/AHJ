package com.example.restapi_ex.jpa.service;

import com.example.restapi_ex.jpa.entity.ItemEntity;
import com.example.restapi_ex.jpa.entity.ItemRepository;
import com.example.restapi_ex.jpa.controller.model.ItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor//빈 주입받을 것을 포함한 생성자 생성
@Service
public class RestExService {
    private final ItemRepository itemRepository;

    public boolean registerItem(ItemDto itemDto){
        //dto -> entity
        ItemEntity entity = new ItemEntity();
        entity.setId(itemDto.getId());
        entity.setName(itemDto.getName());
        //DB insert
        var result = itemRepository.save(entity);
        log.info("result : " + result);
        return true;
    }
}
