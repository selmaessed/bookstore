package com.example.bookstore.bookstore.web;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.example.bookstore.bookstore.domain.Category;
import com.example.bookstore.bookstore.repository.CategoryRepository;

@Component
public class CategoryConverter implements Converter<String, Category> {

    private final CategoryRepository categoryRepository;

    public CategoryConverter(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category convert(String source) {
        Long id = Long.parseLong(source);
        return categoryRepository.findById(id).orElse(null);
    }
}

