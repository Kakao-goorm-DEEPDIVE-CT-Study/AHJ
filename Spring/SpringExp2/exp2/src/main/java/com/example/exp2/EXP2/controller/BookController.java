package com.example.exp2.EXP2.controller;
import com.example.exp2.EXP2.model.Book;
import com.example.exp2.EXP2.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> showBookList(){
        return bookService.showBookList();
    }

    @GetMapping("/books/{title}")
    public Book findBookByTitle(@PathVariable String title){
        return bookService.findBookByTitle(title);
    }

    @PostMapping("/books")
    public String addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    @PutMapping("books/{title}")
    public String editBook(@PathVariable String title, @RequestBody Book book){
        return bookService.editBook(title,book);
    }

    @DeleteMapping("books/{title}")
    public String deleteBook(@PathVariable String title){
        return bookService.deleteBook(title);
    }
}
