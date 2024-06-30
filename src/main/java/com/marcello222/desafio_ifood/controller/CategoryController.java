package com.marcello222.desafio_ifood.controller;

import com.marcello222.desafio_ifood.domain.CategoryDto;
import com.marcello222.desafio_ifood.entities.category.Category;
import com.marcello222.desafio_ifood.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> createdCategory(@RequestBody CategoryDto categoryDto) {
        Category newCategory = this.categoryService.createdCategory(categoryDto);
        return ResponseEntity.ok().body(newCategory);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categories = this.categoryService.getAllCategory();
        return ResponseEntity.ok().body(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") String id, @RequestBody CategoryDto categoryDto) {
        Category updatedCategory = this.categoryService.updateCategory(id, categoryDto);
        return ResponseEntity.ok().body(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") String id) {
        this.categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Category>> getCategory(@PathVariable("id") String id) {
        Optional<Category> category = this.categoryService.getById(id);
        return ResponseEntity.ok().body(category);
    }


}
