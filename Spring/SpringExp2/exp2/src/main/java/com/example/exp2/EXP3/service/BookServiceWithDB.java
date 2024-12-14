package com.example.exp2.EXP3.service;

import com.example.exp2.EXP3.dto.BookRequestDto;
import com.example.exp2.EXP3.dto.BookResponseDto;
import com.example.exp2.EXP3.entity.BookWithDB;
import com.example.exp2.EXP3.entity.BookRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceWithDB {

    private final BookRepository bookRepository;

    public BookServiceWithDB(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<BookResponseDto> showBookList(){
        List<BookWithDB> bookWithDBS = bookRepository.findAll();
        List<BookResponseDto> bookList = new ArrayList<>();

        for(BookWithDB bookWithDB : bookWithDBS) {
            BookResponseDto bookResponseDto = BookResponseDto.builder()
                    .title(bookWithDB.getTitle())
                    .author(bookWithDB.getAuthor())
                    .publisher(bookWithDB.getPublisher())
                    .price(bookWithDB.getPrice())
                    .build();
            bookList.add(bookResponseDto);
        }
        return bookList;
    }

    public BookResponseDto findBookByTitle(String title){
        BookWithDB bookWithDB = bookRepository.findBookByTitle(title);
        return BookResponseDto.builder()
                .title(bookWithDB.getTitle())
                .author(bookWithDB.getAuthor())
                .publisher(bookWithDB.getPublisher())
                .price(bookWithDB.getPrice())
                .build();
    }

    public String addBook(BookRequestDto bookRequestDto){
        BookWithDB bookWithDB = bookRepository.findBookByTitle(bookRequestDto.getTitle());
        if(bookWithDB != null){
            return "동일한 책이 존재합니다";
        }else{
            bookRepository.save(BookWithDB.builder()
                    .title(bookRequestDto.getTitle())
                    .author(bookRequestDto.getAuthor())
                    .publisher(bookRequestDto.getPublisher())
                    .price(bookRequestDto.getPrice())
                    .build());
            return "새로운 책을 추가했습니다";
        }
    }

    public String editBook(String title, BookRequestDto bookRequestDto){
        BookWithDB editBookWithDB = bookRepository.findBookByTitle(title);
        if(editBookWithDB != null){
            editBookWithDB.setTitle(bookRequestDto.getTitle());
            editBookWithDB.setAuthor(bookRequestDto.getAuthor());
            editBookWithDB.setPublisher(bookRequestDto.getPublisher());
            editBookWithDB.setPrice(bookRequestDto.getPrice());
            bookRepository.save(editBookWithDB);
            return "책 정보 수정이 성공했습니다";
        }else{
            return "해당 책은 존재하지 않습니다";
        }
    }

    public String deleteBook(String title){
        BookWithDB deleteBookWithDB = bookRepository.findBookByTitle(title);
        if(deleteBookWithDB != null){
            bookRepository.delete(deleteBookWithDB);
            return "책 정보 삭제에 성공했습니다";
        }else{
            return "해당 책은 존재하지 않습니다";
        }
    }
}
