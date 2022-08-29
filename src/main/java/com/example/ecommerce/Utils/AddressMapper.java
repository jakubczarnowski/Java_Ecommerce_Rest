package com.example.ecommerce.Utils;

import com.example.ecommerce.dto.address.AddressUpdateDto;
import com.example.ecommerce.model.DeliveryAddress;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
@Mapper(componentModel = "spring")
public interface AddressMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAddressFromDto(AddressUpdateDto dto, @MappingTarget DeliveryAddress entity);
}
