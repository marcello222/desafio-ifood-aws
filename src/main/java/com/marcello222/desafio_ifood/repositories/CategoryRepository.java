package com.marcello222.desafio_ifood.repositories;

import com.marcello222.desafio_ifood.entities.category.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
}
