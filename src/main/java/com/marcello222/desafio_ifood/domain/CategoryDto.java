package com.marcello222.desafio_ifood.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

        private String id;

        private String title;

        private String description;

        private String ownerId;


}
