package com.example.starter.book;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.print.Book;

@RequestMapping("api/book")
public class BookController {

    // get all books without description
    // get book by id

    @GetMapping
    public Book getAllBooks() {
        return new Book();
    }

    @GetMapping
    public Book getBookById() {
        return new Book();
    }


}
