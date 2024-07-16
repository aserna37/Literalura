package com.alura.Literalura;


import com.alura.Literalura.principal.Principal;
import com.alura.Literalura.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LiteraluraApplication {


	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(LiteraluraApplication.class, args);

		BookService bookService = context.getBean(BookService.class);
		Principal principal = new Principal(bookService);
		principal.muestraElMenu();
	}

}
