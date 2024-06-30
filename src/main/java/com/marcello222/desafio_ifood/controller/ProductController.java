package com.marcello222.desafio_ifood.controller;

import com.marcello222.desafio_ifood.domain.CategoryDto;
import com.marcello222.desafio_ifood.domain.ProductDto;
import com.marcello222.desafio_ifood.entities.category.Category;
import com.marcello222.desafio_ifood.entities.product.Product;
import com.marcello222.desafio_ifood.mapper.CategoryMapper;
import com.marcello222.desafio_ifood.services.ProductService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductService productService;



    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<Product> createdProduct(@RequestBody ProductDto productDto) {
       Product newProduct = this.productService.createdProduct(productDto);
         return ResponseEntity.ok().body(newProduct);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct() {
      List<Product> product = this.productService.getAllProduct();
        return ResponseEntity.ok().body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") String id, @RequestBody ProductDto productDto) {
        Product updatedProduct = this.productService.updateProduct(id, productDto);
        return ResponseEntity.ok().body(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") String id) {
        this.productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
