package com.example.bookstore.bookstore.web;

import com.example.bookstore.bookstore.domain.Category;
import com.example.bookstore.bookstore.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categorylist"; // templates/categorylist.html
    }

    @GetMapping("/new")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "addcategory"; // templates/addcategory.html
    }

    @PostMapping
    public String saveCategory(@ModelAttribute Category category) {
        categoryRepository.save(category);
        return "redirect:/categories";
    }
}
