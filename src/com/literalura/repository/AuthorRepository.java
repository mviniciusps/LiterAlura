package com.literalura.repository;

import com.literalura.model.Author;
import java.util.ArrayList;
import java.util.List;

public class AuthorRepository {

    private List<Author> authors = new ArrayList<>();

    public void save(Author a) {
        // Evita duplicados
        boolean exists = authors.stream().anyMatch(author -> author.getName().equalsIgnoreCase(a.getName()));
        if(!exists) {
            authors.add(a);
        }
    }

    public List<Author> findAll() {
        return authors;
    }
}