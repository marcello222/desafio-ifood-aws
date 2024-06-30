package com.marcello222.desafio_ifood.services;

import com.marcello222.desafio_ifood.domain.ProductDto;
import com.marcello222.desafio_ifood.entities.category.Category;
import com.marcello222.desafio_ifood.entities.product.Product;
import com.marcello222.desafio_ifood.exceptions.CategoryNotFoundException;
import com.marcello222.desafio_ifood.exceptions.ProductNotFoundException;
import com.marcello222.desafio_ifood.mapper.ProductMapper;
import com.marcello222.desafio_ifood.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, CategoryService categoryService, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.productMapper = productMapper;
    }

    public Product createdProduct(ProductDto productDto) {
        Category category = this.categoryService.getById(productDto.getCategoryId()).orElseThrow(
                ProductNotFoundException::new);

        Product product = this.productMapper.toEntity(productDto);
        product.setCategory(category);
        return this.productRepository.save(product);
    }

    public List<Product> getAllProduct() {
        return this.productRepository.findAll();
    }

    public Product updateProduct(String id, ProductDto productDto) {

        Product product = this.productRepository.findById(id).orElseThrow(
                ProductNotFoundException::new);

        if(productDto.getCategoryId() != null) {
            this.categoryService.getById(productDto.getCategoryId()).ifPresent(product::setCategory);
        }


        Product updatedProduct = this.productMapper.toEntity(productDto);

        if (!updatedProduct.getTitle().isEmpty()) {
            product.setTitle(productDto.getTitle());
        }
        if (!updatedProduct.getDescription().isEmpty()) {
            product.setDescription(productDto.getDescription());
        }
        if (!(productDto.getPrice() == null)) {
            product.setPrice(productDto.getPrice());
        }

        return this.productRepository.save(product);
    }

    public void deleteProduct(String id) {
        Product product = this.productRepository.findById(id).orElseThrow(
                ProductNotFoundException::new);
        this.productRepository.delete(product);
    }

}
