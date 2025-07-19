package com.mongo.service;

import com.mongo.dto.ProductDTO;
import com.mongo.entity.ProductEntity;
import com.mongo.mapper.ProductMapper;
import com.mongo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductDTO productCreate(ProductDTO productDTO){

        ProductEntity productEntity = ProductMapper.INSTANCE.dtoToEntity(productDTO);
        ProductEntity productEntitySaved = productRepository.save(productEntity);

        return productEntitySaved.getId() == null ? null : ProductMapper.INSTANCE.entityToDTO(productEntitySaved);
    }
}
