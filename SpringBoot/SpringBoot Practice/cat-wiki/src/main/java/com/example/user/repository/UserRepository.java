package com.example.user.repository;

import com.example.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
//<Entity, Key>
public interface UserRepository extends JpaRepository<User, Long> {
}
