package com.example.bookstore.bookstore.web;

import com.example.bookstore.bookstore.domain.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    @GetMapping("/index")
    public String index(Model model) {
        // Luodaan yksi esimerkkikirja
        Book book = new Book();
        book.setTitle("Opas Spring Bootiin");
        book.setAuthor("Selma Esimerkki");
        book.setPublicationYear(2026);
        book.setIsbn("123-4567890123");
        book.setPrice(29.90);

        model.addAttribute("book", book); // Lisätään kirja templateen
        return "index"; // Thymeleaf etsii templates/index.html
    }
}
