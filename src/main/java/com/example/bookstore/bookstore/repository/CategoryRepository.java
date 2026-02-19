package com.example.bookstore.bookstore.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.bookstore.bookstore.domain.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
