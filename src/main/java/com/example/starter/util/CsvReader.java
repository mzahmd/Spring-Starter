package com.example.starter.util;

import com.example.starter.model.Author;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


@Component
public class CsvReader {

    public List<Author> readPersons(InputStream inputStream) throws Exception {
        List<Author> persons = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(inputStream))) {
            String[] line;
            reader.readNext();

            while ((line = reader.readNext()) != null) {
                Author author = new Author();
                author.setName(line[0]);
                author.setEmail(line[1]);
                author.setAge(Integer.parseInt(line[2]));
                persons.add(author);
            }
        }
        return persons;
    }
}
