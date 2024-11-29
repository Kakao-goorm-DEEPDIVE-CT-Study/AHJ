package com.example.restapi_ex.hibernate.service;

import com.example.restapi_ex.hibernate.controller.model.ItemHibernateDto;
import com.example.restapi_ex.hibernate.entity.ItemHibernateEntity;
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

    public boolean registerHibernate(ItemHibernateDto itemHibernateDto){
        //dto -> entity
        ItemHibernateEntity entity = new ItemHibernateEntity();
        entity.setId(itemHibernateDto.getId());
        entity.setName(itemHibernateDto.getName());

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
