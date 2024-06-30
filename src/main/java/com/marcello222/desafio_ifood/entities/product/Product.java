package com.marcello222.desafio_ifood.entities.product;


import com.marcello222.desafio_ifood.domain.ProductDto;
import com.marcello222.desafio_ifood.entities.category.Category;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "products")
public class Product {

    @Id
    private String id;

    private String title;

    private String description;

    private String ownerId;

    private Integer price;

    private Category category;

    public Product(ProductDto productDto) {
        this.title = productDto.getTitle();
        this.description = productDto.getDescription();
        this.ownerId = productDto.getOwnerId();
        this.price = productDto.getPrice();
    }

}
