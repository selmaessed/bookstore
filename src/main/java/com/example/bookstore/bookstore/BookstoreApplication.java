package com.example.bookstore.bookstore;

import com.example.bookstore.bookstore.domain.Book;
import com.example.bookstore.bookstore.repository.bookrepository; // Oikea kirjainkoko
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(bookrepository repository) {
		return (args) -> {
			// Luodaan esimerkkikirjat
			repository.save(new Book("The Hobbit", "J.R.R. Tolkien", 1937, "978-0547928227", 15.99));
			repository.save(new Book("1984", "George Orwell", 1949, "978-0451524935", 12.50));
			repository.save(new Book("Harry Potter", "J.K. Rowling", 1997, "978-0590353427", 20.00));
		};
	}
}
