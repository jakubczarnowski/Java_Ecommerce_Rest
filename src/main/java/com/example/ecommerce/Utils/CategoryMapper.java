package com.example.ecommerce.Utils;

import com.example.ecommerce.dto.category.CategoryEditDto;
import com.example.ecommerce.model.Category;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDto(CategoryEditDto dto, @MappingTarget Category entity);
}
