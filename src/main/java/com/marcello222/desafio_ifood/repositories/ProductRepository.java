package com.marcello222.desafio_ifood.repositories;

import com.marcello222.desafio_ifood.entities.product.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
