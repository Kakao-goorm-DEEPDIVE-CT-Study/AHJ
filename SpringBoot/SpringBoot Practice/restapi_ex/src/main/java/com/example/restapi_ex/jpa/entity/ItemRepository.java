package com.example.restapi_ex.jpa.controller.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, String> {
}
