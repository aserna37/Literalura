package com.alura.Literalura.api;

import com.alura.Literalura.model.Book;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Component
public class GutendexClient {
    private static final String API_URL = "https://gutendex.com/books";

    public List<Book> searchBooksByTitle(String title) {
        String url = API_URL + "?title=" + URLEncoder.encode(title, StandardCharsets.UTF_8);
        return fetchBooksFromApi(url);
    }

    public List<Book> searchBooksByLanguage(String language) {
        String url = API_URL + "?languages=" + URLEncoder.encode(language, StandardCharsets.UTF_8);
        return fetchBooksFromApi(url);
    }

    private List<Book> fetchBooksFromApi(String url) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(new URL(url));
            JsonNode results = root.get("results");
            List<Book> books = new ArrayList<>();
            for (JsonNode node : results) {
                Book book = new Book();
                book.setTitle(node.get("title").asText());
                book.setLanguage(node.get("languages").get(0).asText());
                book.setAuthor(node.get("authors").get(0).get("name").asText());
                // Mapear otros campos según sea necesario
                books.add(book);
            }
            return books;
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
