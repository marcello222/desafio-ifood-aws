package com.marcello222.desafio_ifood.entities.category;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "categories")
public class Category {

    @Id
    private String id;

    private String title;

    private String description;

    private String ownerId;

}
