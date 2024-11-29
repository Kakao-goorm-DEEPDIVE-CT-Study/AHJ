package com.example.restapi_ex.service;

import com.example.restapi_ex.controller.entity.ItemEntity;
import com.example.restapi_ex.controller.model.ItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor//빈 주입받을 것을 포함한 생성자 생성
@Service
public class HibernateService {

    @Autowired
    private final SessionFactory sessionFactory;

    public boolean registerHibernate(ItemDto itemDto){
        //dto -> entity
        ItemEntity entity = new ItemEntity();
        entity.setId(itemDto.getId());
        entity.setName(itemDto.getName());

        //DB insert
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        var saveEntity = session.save(entity);
        session.getTransaction().commit();
        session.close();

        log.info("save item entity : " + saveEntity );
        return true;
    }
}
