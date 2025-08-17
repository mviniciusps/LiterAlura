package com.literalura.service;

import com.literalura.model.*;
import com.literalura.repository.*;
import java.util.List;
import java.util.Iterator;

public class BookService {

    private BookRepository bookRepo;
    private AuthorRepository authorRepo;

    public BookService(BookRepository b, AuthorRepository a) {
        this.bookRepo = b;
        this.authorRepo = a;
    }

    public void addBook(Book book) {
        bookRepo.save(book);
        authorRepo.save(book.getAuthor());
    }

    public void listBooks() {
        List<Book> books = bookRepo.findAll();
        if(books.isEmpty()) {
            System.out.println("Nenhum livro cadastrado.");
        } else {
            for(Book b : books) {
                System.out.println(b);
            }
        }
    }

    public void listAuthors() {
        List<Author> authors = authorRepo.findAll();
        if(authors.isEmpty()) {
            System.out.println("Nenhum autor cadastrado.");
        } else {
            for(Author a : authors) {
                System.out.println(a);
            }
        }
    }

    // Novo método para remover livro pelo título
    public void removeBook(String title) {
        List<Book> books = bookRepo.findAll();
        Iterator<Book> it = books.iterator();
        boolean removed = false;
        while(it.hasNext()) {
            Book b = it.next();
            if(b.getTitle().equalsIgnoreCase(title)) {
                it.remove();
                removed = true;
                System.out.println("Livro removido: " + b.getTitle());
            }
        }
        if(!removed) {
            System.out.println("Livro não encontrado para remoção.");
        }
    }

    // Para buscar livros no ConsoleApp
    public BookRepository getBookRepository() {
        return bookRepo;
    }

    public AuthorRepository getAuthorRepository() {
        return authorRepo;
    }
}