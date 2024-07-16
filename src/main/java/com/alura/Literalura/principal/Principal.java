package com.alura.Literalura.principal;

import com.alura.Literalura.model.Author;
import com.alura.Literalura.model.Book;
import com.alura.Literalura.service.BookService;

import java.util.List;
import java.util.Scanner;

public class Principal {

    private final BookService bookService;

    public Principal(BookService bookService) {
        this.bookService = bookService;
    }

    public void muestraElMenu() {
        if (bookService == null) {
            System.out.println("bookService es null. Verifica la configuración de la inyección de dependencias.");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Elija la opción a través de su número:");
            System.out.println("1- buscar libro por título");
            System.out.println("2- listar libros registrados");
            System.out.println("3- listar autores registrados");
            System.out.println("4- listar autores vivos en un determinado año");
            System.out.println("5- listar libros por idioma");
            System.out.println("0- salir");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume la nueva línea

            switch (option) {
                case 1:
                    System.out.print("Ingrese el título del libro: ");
                    String title = scanner.nextLine();
                    List<Book> books = bookService.searchBooksByTitle(title);
                    books.forEach(System.out::println);
                    break;
                case 2:
                    List<Book> allBooks = bookService.listAllBooks();
                    allBooks.forEach(System.out::println);
                    break;
                case 3:
                    List<Author> allAuthors = bookService.listAllAuthors();
                    allAuthors.forEach(System.out::println);
                    break;
                case 4:
                    System.out.print("Ingrese el año: ");
                    int year = scanner.nextInt();
                    List<Author> authors = bookService.listAuthorsAliveInYear(year);
                    authors.forEach(System.out::println);
                    break;
                case 5:
                    System.out.print("Ingrese el idioma: ");
                    String language = scanner.nextLine();
                    List<Book> booksByLanguage = bookService.listBooksByLanguage(language);
                    booksByLanguage.forEach(System.out::println);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }
}

