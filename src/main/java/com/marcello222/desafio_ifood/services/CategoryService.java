package com.marcello222.desafio_ifood.services;

import com.marcello222.desafio_ifood.domain.CategoryDto;
import com.marcello222.desafio_ifood.entities.category.Category;
import com.marcello222.desafio_ifood.exceptions.CategoryNotFoundException;
import com.marcello222.desafio_ifood.mapper.CategoryMapper;
import com.marcello222.desafio_ifood.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    public Category createdCategory(CategoryDto categoryDto) {
        Category categoryMapper = this.categoryMapper.toEntity(categoryDto);
        return this.categoryRepository.save(categoryMapper);
    }

    public List<Category> getAllCategory() {
        return this.categoryRepository.findAll();
    }

    public Category updateCategory(String id, CategoryDto categoryDto) {

        Category category = this.categoryRepository.findById(id).orElseThrow(
                CategoryNotFoundException::new);

        Category updatedCategory = this.categoryMapper.toEntity(categoryDto);

        if (!updatedCategory.getTitle().isEmpty()) {
            category.setTitle(categoryDto.getTitle());
        }
        if (!updatedCategory.getDescription().isEmpty()) {
            category.setDescription(categoryDto.getDescription());
        }
        return this.categoryRepository.save(category);
    }

    public void deleteCategory(String id) {
        Category category = this.categoryRepository.findById(id).orElseThrow(
                CategoryNotFoundException::new);
        this.categoryRepository.delete(category);
    }

    public Optional<Category> getById(String id) {
        return this.categoryRepository.findById(id);
    }

}
