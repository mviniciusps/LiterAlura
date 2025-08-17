package com.literalura.repository;

import com.literalura.model.Book;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {

    private List<Book> books = new ArrayList<>();

    public void save(Book b) {
        books.add(b);
    }

    public List<Book> findAll() {
        return books;
    }
}