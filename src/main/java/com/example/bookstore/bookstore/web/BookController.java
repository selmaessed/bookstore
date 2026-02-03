package com.example.bookstore.bookstore.web;

import com.example.bookstore.bookstore.domain.Book;
import com.example.bookstore.bookstore.repository.bookrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BookController {

    @Autowired
    private bookrepository repository;

    // Näytä kirjalista
    @GetMapping("/booklist")
    public String showBookList(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }

    // Näytä lomake uuden kirjan lisäämiseksi
    @GetMapping("/addbook")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }

    // Käsittele uuden kirjan lisääminen
    @PostMapping("/addbook")
    public String addBook(@ModelAttribute Book book) {
        repository.save(book);
        return "redirect:/booklist";
    }

    // Poista kirja ID:n perusteella
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return "redirect:/booklist";
    }
}
