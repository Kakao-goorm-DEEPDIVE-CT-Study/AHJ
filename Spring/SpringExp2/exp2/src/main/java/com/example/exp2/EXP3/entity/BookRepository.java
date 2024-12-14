package com.example.exp2.EXP3.entity;


import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookWithDB, Long> {
    BookWithDB findBookByTitle(String title);
}
