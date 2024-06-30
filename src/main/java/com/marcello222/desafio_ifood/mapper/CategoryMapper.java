package com.marcello222.desafio_ifood.mapper;

import com.marcello222.desafio_ifood.domain.CategoryDto;
import com.marcello222.desafio_ifood.entities.category.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "id", ignore = true)
    Category toEntity(CategoryDto categoryDto);

    CategoryDto toDto(Category category);
}
