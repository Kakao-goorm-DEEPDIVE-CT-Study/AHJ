package com.example.restapi_ex.controller.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "item")
public class ItemEntity {

    @Id
    private String id;
    private String name;
}
