package com.example.bookstore.bookstore.web;

import com.example.bookstore.bookstore.domain.Book;
import com.example.bookstore.bookstore.repository.BookRepositoryTemp;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private final BookRepositoryTemp bookRepository;

    public BookRestController(BookRepositoryTemp bookRepository) {
        this.bookRepository = bookRepository;
    }

    // a) Palauttaa kaikki kirjat JSON-muodossa
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // b) Palauttaa yhden kirjan id:n perusteella JSON-muodossa
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookRepository.findById(id).orElseThrow();
    }
}