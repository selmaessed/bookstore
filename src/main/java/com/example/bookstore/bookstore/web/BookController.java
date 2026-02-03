package com.example.bookstore.bookstore.web;

import com.example.bookstore.bookstore.domain.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    @GetMapping("/")
    public String getBooks(Model model) {
        // Luodaan esimerkkilista kirjoista
        List<Book> books = new ArrayList<>();

        books.add(createBook("The Hobbit", "J.R.R. Tolkien", 1937, "978-0547928227", 15.99));
        books.add(createBook("1984", "George Orwell", 1949, "978-0451524935", 12.50));
        books.add(createBook("Harry Potter", "J.K. Rowling", 1997, "978-0590353427", 20.00));

        // Lisätään lista malliin
        model.addAttribute("books", books);

        // Palautetaan index.html
        return "index";
    }

    // Apumetodi kirjojen luomiseen
    private Book createBook(String title, String author, int year, String isbn, double price) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublicationYear(year);
        book.setIsbn(isbn);
        book.setPrice(price);
        return book;
    }
}
