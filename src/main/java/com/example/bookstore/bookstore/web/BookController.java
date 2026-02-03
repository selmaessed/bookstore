package com.example.bookstore.bookstore.web;

import com.example.bookstore.bookstore.domain.Book;
import com.example.bookstore.bookstore.repository.bookrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    @Autowired
    private bookrepository bookRepository;

    // Nykyinen index-sivu
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "index";
    }

    // Uusi GET-metodi booklist-sivulle
    @GetMapping("/booklist")
    public String getBookList(Model model) {
        model.addAttribute("books", bookRepository.findAll());
        return "booklist";
    }
}
