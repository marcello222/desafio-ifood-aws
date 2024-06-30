package com.marcello222.desafio_ifood.template;

import com.marcello222.desafio_ifood.domain.CategoryDto;

public class CategoryDtoTemplate {

    public static CategoryDto getDefaultCategoryDto() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setTitle("Test Title");
        categoryDto.setDescription("Test Description");
        categoryDto.setOwnerId("Test Owner");
        return categoryDto;
    }

}
