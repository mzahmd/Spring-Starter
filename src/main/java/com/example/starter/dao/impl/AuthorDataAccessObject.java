package com.example.starter.dao.impl;

import com.example.starter.dao.AuthorDAO;
import com.example.starter.model.Author;
import com.example.starter.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorDataAccessObject implements AuthorDAO {

    private final AuthorRepository authorRepository;

    public AuthorDataAccessObject(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void createUpdateAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void createUpdateAuthors(List<Author> authors) {
        authorRepository.saveAll(authors);
    }

    @Override
    public Optional<Author> findAuthor(Long id) {
        return authorRepository.findAuthorById(id);
    }

    @Override
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

}
