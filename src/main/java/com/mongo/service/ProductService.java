package com.mongo.service;

import com.mongo.dto.ProductDTO;
import com.mongo.entity.ProductEntity;
import com.mongo.enums.ProductTypeEnum;
import com.mongo.mapper.ProductMapper;
import com.mongo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductDTO productCreate(ProductDTO productDTO){

        ProductEntity productEntity = ProductMapper.INSTANCE.dtoToEntity(productDTO);
        productEntity.setCreateDate(LocalDateTime.now());
        productEntity.setProductTypeEnum(ProductTypeEnum.NEW);
        ProductEntity productEntitySaved = productRepository.save(productEntity);

        return productEntitySaved.getId() == null ? null : ProductMapper.INSTANCE.entityToDTO(productEntitySaved);
    }

    public List<ProductDTO> retrieveProducts() {

        List<ProductEntity> productEntityList = productRepository.findAll();

        if (CollectionUtils.isEmpty(productEntityList)) {
            return null;
        } else {
            return ProductMapper.INSTANCE.entityListToDTOList(productEntityList);
        }

    }

    private ProductEntity getItemEntity(String productId){

        Optional<ProductEntity> itemEntityOpt = productRepository.findById(productId);

        return !itemEntityOpt.isPresent() ? null : itemEntityOpt.get();
    }

    public ProductDTO retrieveProduct(String productId){

        ProductEntity productEntity = getItemEntity(productId);

        return productEntity == null ? null : ProductMapper.INSTANCE.entityToDTO(productEntity);
    }



}
