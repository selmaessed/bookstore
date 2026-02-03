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

    // List page
    @GetMapping("/booklist")
    public String bookList(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }

    // Add book page
    @GetMapping("/addbook")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }

    @PostMapping("/addbook")
    public String saveBook(Book book) {
        repository.save(book);
        return "redirect:/booklist";
    }

    // Delete book
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        repository.deleteById(id);
        return "redirect:/booklist";
    }

    // ✅ Edit book page (GET)
    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        Book book = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("book", book);
        return "editbook";
    }

    // ✅ Save edited book (POST)
    @PostMapping("/edit")
    public String updateBook(Book book) {
        repository.save(book); // save toimii myös päivitykseen, koska id on mukana
        return "redirect:/booklist";
    }
}
