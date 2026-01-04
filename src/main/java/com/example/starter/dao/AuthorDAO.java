package com.example.starter.dao;

import com.example.starter.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDAO {

    void createUpdateAuthor(Author author);
    void createUpdateAuthors(List<Author> author);
    Optional<Author> findAuthor(Long id);
    List<Author> findAllAuthors();
    void deleteAuthor(Long id);
}
