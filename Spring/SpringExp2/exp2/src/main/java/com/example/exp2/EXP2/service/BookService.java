package com.example.exp2.EXP2.service;

import com.example.exp2.EXP2.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    private final List<Book> bookList = new ArrayList<>();

    public List<Book> showBookList(){
        return bookList;
    }

    public Book findBookByTitle(String title){
        return bookList.stream().filter(book -> book.getTitle().equals(title)).findFirst().orElse(null);
    }

    public String addBook(Book book){
        if(bookList.contains(book)){
            return "동일한 책이 존재합니다";
        }else{
            bookList.add(book);
            return "새로운 책을 추가했습니다";
        }
    }

    public String editBook(String title,Book book){
        Book editBook = bookList.stream().filter(editbook -> editbook.getTitle().equals(title)).findFirst().orElse(null);
        if(editBook != null){
            editBook.setTitle(book.getTitle());
            editBook.setAuthor(book.getAuthor());
            editBook.setPublisher(book.getPublisher());
            editBook.setPrice(book.getPrice());
            return "책 정보 수정이 성공했습니다";
        }else{
            return "해당 책은 존재하지 않습니다";
        }
    }

    public String deleteBook(String title){
        Book deleteBook = bookList.stream().filter(editbook -> editbook.getTitle().equals(title)).findFirst().orElse(null);
        if(deleteBook != null){
            bookList.remove(deleteBook);
            return "책 정보 삭제에 성공했습니다";
        }else{
            return "해당 책은 존재하지 않습니다";
        }
    }
}
