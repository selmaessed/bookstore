package com.example.bookstore.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.bookstore.bookstore.domain.Book;

public interface bookrepository extends JpaRepository<Book, Long> {
}
