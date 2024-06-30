package com.marcello222.desafio_ifood.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String id;

    private String title;

    private String description;

    private String ownerId;

    private Integer price;

    private String categoryId;

}
