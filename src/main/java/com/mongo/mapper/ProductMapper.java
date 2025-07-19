package com.mongo.mapper;


import com.mongo.dto.ProductDTO;
import com.mongo.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductEntity dtoToEntity(ProductDTO productDTO);
    ProductDTO entityToDTO (ProductEntity productEntity);

    List<ProductDTO> entityListToDTOList(List<ProductEntity> productEntityList);



}
