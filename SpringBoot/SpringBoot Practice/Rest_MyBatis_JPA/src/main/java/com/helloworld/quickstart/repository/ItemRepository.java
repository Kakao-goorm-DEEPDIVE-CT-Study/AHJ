package com.helloworld.quickstart.repository;

import com.helloworld.quickstart.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, String> {
}
