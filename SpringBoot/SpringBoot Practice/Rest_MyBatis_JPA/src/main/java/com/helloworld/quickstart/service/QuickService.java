package com.helloworld.quickstart.service;

import com.helloworld.quickstart.dto.ItemDto;
import com.helloworld.quickstart.entity.ItemEntity;
import com.helloworld.quickstart.mapper.QuickMapper;
import com.helloworld.quickstart.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Slf4j
public class QuickService {
    @Autowired
    QuickMapper quickMapper;
    @Autowired
    ItemRepository itemRepository;

    public boolean registerItemByMyBatis(ItemDto itemDto){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("id",itemDto.getId());
        paramMap.put("name",itemDto.getName());
        quickMapper.registerItem(paramMap);
        return true;
    }

    public boolean registerItemByJPA(ItemDto itemDto){
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setId(itemDto.getId());
        itemEntity.setName(itemDto.getName());
        itemRepository.save(itemEntity);
        return true;
    }

    public ItemDto getItemByIdMyBatis(String id){
        HashMap<String, Object> paramMap = new HashMap<String,Object>();
        paramMap.put("id",id);
        HashMap<String, Object> resultMap = quickMapper.findById(paramMap);
        ItemDto itemDto = new ItemDto();
        itemDto.setId((String)resultMap.get("ID"));
        itemDto.setName((String)resultMap.get("NAME"));
        return itemDto;
    }

    public ItemDto getItemByIdJPA(String id){
        ItemEntity itemEntity = itemRepository.findById(id).get();
        ItemDto itemDto = new ItemDto();
        itemDto.setId(itemEntity.getId());
        itemDto.setName(itemEntity.getName());
        return itemDto;
    }
}
