package com.literalura.console;

import java.util.Scanner;
import com.literalura.model.*;
import com.literalura.repository.*;
import com.literalura.service.*;

public class ConsoleApp {
    private BookService service;
    private ExternalBookApiService api = new ExternalBookApiService();

    public ConsoleApp() {
        service = new BookService(new BookRepository(), new AuthorRepository());
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        int option = -1;

        while(option != 0) {
            System.out.println("\n=== LiterAlura ===");
            System.out.println("1. Adicionar livro");
            System.out.println("2. Listar livros");
            System.out.println("3. Listar autores");
            System.out.println("4. Buscar livro por título");
            System.out.println("5. Remover livro por título");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            option = Integer.parseInt(sc.nextLine());

            switch(option) {
                case 1:
                    System.out.print("Título: ");
                    String t = sc.nextLine();
                    Book b = api.fetchBook(t);
                    service.addBook(b);
                    System.out.println("Livro adicionado!");
                    break;
                case 2:
                    service.listBooks();
                    break;
                case 3:
                    service.listAuthors();
                    break;
                case 4:
                    System.out.print("Título para buscar: ");
                    String searchTitle = sc.nextLine();
                    boolean found = false;
                    for(Book book : service.getBookRepository().findAll()) {
                        if(book.getTitle().equalsIgnoreCase(searchTitle)) {
                            System.out.println(book);
                            found = true;
                        }
                    }
                    if(!found) System.out.println("Livro não encontrado.");
                    break;
                case 5:
                    System.out.print("Título para remover: ");
                    String removeTitle = sc.nextLine();
                    service.removeBook(removeTitle);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        sc.close();
    }
}