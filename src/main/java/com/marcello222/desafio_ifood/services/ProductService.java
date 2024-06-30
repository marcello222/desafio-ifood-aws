package com.marcello222.desafio_ifood.services;

import com.marcello222.desafio_ifood.domain.ProductDto;
import com.marcello222.desafio_ifood.entities.category.Category;
import com.marcello222.desafio_ifood.entities.product.Product;
import com.marcello222.desafio_ifood.exceptions.CategoryNotFoundException;
import com.marcello222.desafio_ifood.exceptions.ProductNotFoundException;
import com.marcello222.desafio_ifood.mapper.ProductMapper;
import com.marcello222.desafio_ifood.repositories.ProductRepository;
import com.marcello222.desafio_ifood.services.aws.AwsSnsService;
import com.marcello222.desafio_ifood.services.aws.MessageDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    private final ProductMapper productMapper;

    private final AwsSnsService awsSnsService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService, ProductMapper productMapper, AwsSnsService awsSnsService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.productMapper = productMapper;
        this.awsSnsService = awsSnsService;
    }

    public Product createdProduct(ProductDto productDto) {
        Category category = this.categoryService.getById(productDto.getCategoryId()).orElseThrow(
                ProductNotFoundException::new);

        Product newProduct = this.productMapper.toEntity(productDto);
        newProduct.setCategory(category);

        this.productRepository.save(newProduct);

        this.awsSnsService.publish(new MessageDto(newProduct.getOwnerId()));

        return newProduct;

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

        this.productRepository.save(product);

        this.awsSnsService.publish(new MessageDto(product.getOwnerId()));

        return product;
    }

    public void deleteProduct(String id) {
        Product product = this.productRepository.findById(id).orElseThrow(
                ProductNotFoundException::new);
        this.productRepository.delete(product);
    }

    public Product getProductById(String id) {
        return this.productRepository.findById(id).orElseThrow(
                ProductNotFoundException::new);
    }
}
