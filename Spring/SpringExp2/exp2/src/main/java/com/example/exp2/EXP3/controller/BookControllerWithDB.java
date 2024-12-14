package com.example.exp2.EXP3.controller;
import com.example.exp2.EXP3.dto.BookRequestDto;
import com.example.exp2.EXP3.dto.BookResponseDto;
import com.example.exp2.EXP3.service.BookServiceWithDB;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookControllerWithDB {
    private final BookServiceWithDB bookServiceWithDB;

    public BookControllerWithDB(BookServiceWithDB bookServiceWithDB){
        this.bookServiceWithDB = bookServiceWithDB;
    }

    @GetMapping("/booksdb")
    public List<BookResponseDto> showBookList(){
        return bookServiceWithDB.showBookList();
    }

    @GetMapping("/booksdb/{title}")
    public BookResponseDto findBookByTitle(@PathVariable String title){
        return bookServiceWithDB.findBookByTitle(title);
    }

    @PostMapping("/booksdb")
    public String addBook(@RequestBody BookRequestDto bookRequestDto){
        return bookServiceWithDB.addBook(bookRequestDto);
    }

    @PutMapping("booksdb/{title}")
    public String editBook(@PathVariable String title, @RequestBody BookRequestDto bookRequestDto){
        return bookServiceWithDB.editBook(title,bookRequestDto);
    }

    @DeleteMapping("booksdb/{title}")
    public String deleteBook(@PathVariable String title){
        return bookServiceWithDB.deleteBook(title);
    }
}
