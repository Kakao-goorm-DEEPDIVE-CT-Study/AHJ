package com.example.restapi_ex.hibernate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "item")
public class ItemHibernateEntity {

    @Id
    private String id;
    private String name;
}
