package com.example.bookstore.bookstore;

import com.example.bookstore.bookstore.domain.Book;
import com.example.bookstore.bookstore.domain.Category;
import com.example.bookstore.bookstore.repository.bookrepository;
import com.example.bookstore.bookstore.repository.CategoryRepository;
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
	public CommandLineRunner demoCategoriesAndBooks(CategoryRepository categoryRepository, bookrepository bookRepository) {
		return (args) -> {
			// Lisää kategoriat
			Category scifi = categoryRepository.save(new Category("Scifi"));
			Category comic = categoryRepository.save(new Category("Comic"));
			Category mystery = categoryRepository.save(new Category("Mystery"));

			System.out.println("Categories in database:");
			categoryRepository.findAll()
					.forEach(category -> System.out.println(category.getCategoryid() + ": " + category.getName()));

			// Lisää kirjat kategorioiden kanssa
			bookRepository.save(new Book("The Hobbit", "J.R.R. Tolkien", 1937, "978-0547928227", 15.99, scifi));
			bookRepository.save(new Book("1984", "George Orwell", 1949, "978-0451524935", 12.50, scifi));
			bookRepository.save(new Book("Harry Potter", "J.K. Rowling", 1997, "978-0590353427", 20.00, comic));
		};
	}
}
