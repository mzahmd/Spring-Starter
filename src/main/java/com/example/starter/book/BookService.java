package com.example.starter.book;

import java.util.List;
import java.util.Optional;

public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getBookById(String id) {
        Optional<Book> bookOptional = bookRepository.findById(id);

        return bookOptional.orElse(null);
    }

    public List<Book> getAllBooks(String name) {
        return bookRepository.findAll();
    }

}

