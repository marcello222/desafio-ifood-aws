package com.marcello222.desafio_ifood.template;


import com.marcello222.desafio_ifood.domain.ProductDto;

public class ProductDtoTemplate {

    public static ProductDto ProductTemplate() {
        ProductDto productDto = new ProductDto();
        productDto.setTitle("Test Title");
        productDto.setDescription("Test Description");
        productDto.setPrice(10);
        productDto.setCategoryId("Test Category");
        productDto.setOwnerId("Test Owner");
        return productDto;
    }

}
