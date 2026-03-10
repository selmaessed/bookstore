package com.example.bookstore.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.bookstore.bookstore.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
