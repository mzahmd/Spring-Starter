package com.example.starter.service;

import com.example.starter.dao.AuthorDAO;
import com.example.starter.dto.AuthorDTO;
import com.example.starter.model.Author;
import com.example.starter.util.CsvReader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorDAO authorDAO;
    private final CsvReader csvReader;

    public AuthorService(AuthorDAO authorDAO, CsvReader csvReader) {
        this.authorDAO = authorDAO;
        this.csvReader = csvReader;
    }

    public AuthorDTO findById(Long id) {
        Optional<Author> author = authorDAO.findAuthor(id);

        if(author.isEmpty()) {
            throw new RuntimeException("Author not found");
        }

        return new AuthorDTO(author.get().getName(), author.get().getEmail(), author.get().getAge());

    }

    public List<AuthorDTO> findAll() {
        return authorDAO.findAllAuthors().stream()
                .map(author -> new AuthorDTO(author.getName(), author.getEmail(), author.getAge()))
                .toList();
    }

    public void create(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setAge(authorDTO.age());
        author.setEmail(authorDTO.email());
        author.setName(authorDTO.name());

        authorDAO.createUpdateAuthor(author);
    }

    public void delete(Long id) {
        authorDAO.deleteAuthor(id);
    }

    public void saveFromCsv(MultipartFile file) throws Exception {
        List<Author> authorsList = csvReader.readPersons(file.getInputStream());

        authorDAO.createUpdateAuthors(authorsList);
    }

    public AuthorDTO update(Long id, AuthorDTO authorDTO) {
        Optional<Author> authorOptional = authorDAO.findAuthor(id);

        if(authorOptional.isEmpty()) {
            throw new RuntimeException("Author not found");
        }

        Author author = authorOptional.get();

        author.setName(authorDTO.name());
        author.setEmail(authorDTO.email());
        author.setAge(authorDTO.age());

        authorDAO.createUpdateAuthor(author);

        return authorDTO;
    }
}
