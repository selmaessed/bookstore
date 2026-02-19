package com.example.bookstore.bookstore;

import com.example.bookstore.bookstore.domain.Book;
import com.example.bookstore.bookstore.domain.Category;
import com.example.bookstore.bookstore.repository.bookrepository;
import com.example.bookstore.bookstore.repository.CategoryRepository; // uusi
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	// Vanha Book CommandLineRunner
	@Bean
	public CommandLineRunner demoBooks(bookrepository repository) {
		return (args) -> {
			repository.save(new Book("The Hobbit", "J.R.R. Tolkien", 1937, "978-0547928227", 15.99));
			repository.save(new Book("1984", "George Orwell", 1949, "978-0451524935", 12.50));
			repository.save(new Book("Harry Potter", "J.K. Rowling", 1997, "978-0590353427", 20.00));
		};
	}

	// Uusi Category CommandLineRunner
	@Bean
	public CommandLineRunner demoCategories(CategoryRepository repository) {
		return (args) -> {
			repository.save(new Category("Scifi"));
			repository.save(new Category("Comic"));
			repository.save(new Category("Mystery"));

			System.out.println("Categories in database:");
			repository.findAll()
					.forEach(category -> System.out.println(category.getCategoryid() + ": " + category.getName()));
		};
	}
}
