package com.alura.Literalura.service;

import com.alura.Literalura.api.GutendexClient;
import com.alura.Literalura.model.Author;
import com.alura.Literalura.model.Book;
import com.alura.Literalura.repository.AuthorRepository;
import com.alura.Literalura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GutendexClient gutendexClient;



    public List<Book> searchBooksByTitle(String title) {
        List<Book> books = bookRepository.findByTitleContaining(title);
        if (books.isEmpty()) {
            books = gutendexClient.searchBooksByTitle(title);
            bookRepository.saveAll(books);
        }
        return books;
    }

    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    public List<Author> listAllAuthors() {
        return authorRepository.findAll();
    }

    public List<Author> listAuthorsAliveInYear(int year) {
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);
        return authorRepository.findByBirthDateBeforeAndDeathDateAfter(startDate, endDate);
    }

    public List<Book> listBooksByLanguage(String language) {
        List<Book> books = bookRepository.findByLanguage(language);
        if (books.isEmpty()) {
            books = gutendexClient.searchBooksByLanguage(language);
            bookRepository.saveAll(books);
        }
        return books;
    }


}
