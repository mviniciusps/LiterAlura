package com.literalura.service;

import com.literalura.model.Book;
import com.literalura.model.Author;
import java.net.http.*;
import java.net.URI;
import java.io.IOException;

public class ExternalBookApiService {

    private static final String API_URL = "https://gutendex.com/books?search=";

    public Book fetchBook(String title) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + title.replace(" ", "+")))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();

            // Parsing simples para extrair título e autor do JSON (primeiro resultado)
            String bookTitle = extractField(body, "\"title\":\"");
            String authorName = extractField(body, "\"name\":\"");

            if (bookTitle.isEmpty()) bookTitle = title;
            if (authorName.isEmpty()) authorName = "Autor Desconhecido";

            Author author = new Author(authorName);
            return new Book(bookTitle, author);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        // fallback
        return new Book(title, new Author("Autor Desconhecido"));
    }

    // Função simples para extrair valor de campo do JSON (apenas primeira ocorrência)
    private String extractField(String json, String key) {
        int index = json.indexOf(key);
        if (index == -1) return "";
        int start = index + key.length();
        int end = json.indexOf("\"", start);
        if (end == -1) return "";
        return json.substring(start, end);
    }
}