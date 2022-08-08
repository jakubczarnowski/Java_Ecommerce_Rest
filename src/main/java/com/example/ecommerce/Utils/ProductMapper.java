package com.example.ecommerce.Utils;

import com.example.ecommerce.dto.product.ProductEditDto;
import com.example.ecommerce.model.Product;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProductMapper
{
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDto(ProductEditDto dto, @MappingTarget Product entity);
}
