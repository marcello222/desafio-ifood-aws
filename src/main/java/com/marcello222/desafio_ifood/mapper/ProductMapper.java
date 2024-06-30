package com.marcello222.desafio_ifood.mapper;

import com.marcello222.desafio_ifood.domain.ProductDto;
import com.marcello222.desafio_ifood.entities.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    Product toEntity(ProductDto productDto);

    ProductDto toDto(Product productDto);
}
