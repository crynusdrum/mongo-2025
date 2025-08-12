package com.mongo.service;

import com.mongo.dto.ProductDTO;
import com.mongo.dto.SearchDTO;
import com.mongo.entity.ProductEntity;
import com.mongo.enums.ProductTypeEnum;
import com.mongo.mapper.ProductMapper;
import com.mongo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final MongoTemplate mongoTemplate;
    private final FilterSpecificationService<ProductEntity> filterSpecificationService;

    public Page<ProductDTO> retrieveProducts(String id,
                                             String description,
                                             Pageable pageable) {

        List<SearchDTO> searchDtoFilterList = new ArrayList<>();

        if (id != null) {
            searchDtoFilterList.add(new SearchDTO("id", id));
        }
        if (description != null) {
            searchDtoFilterList.add(new SearchDTO("description", description));
        }


        //Filtering
        Query query = filterSpecificationService.getSearchSpecification(searchDtoFilterList);
        //Pagination
        query.with(pageable);
        List<ProductEntity> productEntityList = mongoTemplate.find(query, ProductEntity.class);

        Query countQuery = filterSpecificationService.getSearchSpecification(searchDtoFilterList);
        long total = mongoTemplate.count(countQuery, ProductEntity.class);

        if(CollectionUtils.isEmpty(productEntityList)){
            return null;
        }else{
            List<ProductDTO> productDTOList = ProductMapper.INSTANCE.entityListToDTOList(productEntityList);
            return new PageImpl<>(productDTOList, pageable, total);
        }
    }

    public ProductDTO productCreate(ProductDTO productDTO){

        ProductEntity productEntity = ProductMapper.INSTANCE.dtoToEntity(productDTO);
        productEntity.setCreateDate(LocalDateTime.now());
        productEntity.setProductTypeEnum(ProductTypeEnum.NEW);
        ProductEntity productEntitySaved = productRepository.save(productEntity);

        return productEntitySaved.getId() == null ? null : ProductMapper.INSTANCE.entityToDTO(productEntitySaved);
    }

    private ProductEntity getItemEntity(String productId){

        Optional<ProductEntity> itemEntityOpt = productRepository.findById(productId);

        return !itemEntityOpt.isPresent() ? null : itemEntityOpt.get();
    }

    public ProductDTO retrieveProduct(String productId){

        ProductEntity productEntity = getItemEntity(productId);

        return productEntity == null ? null : ProductMapper.INSTANCE.entityToDTO(productEntity);
    }

    @Transactional
    public ProductDTO productUpdate(String itemId, ProductDTO productDTORequest) {

        ProductEntity productEntityFromDB = getItemEntity(itemId);

        if (productEntityFromDB == null) {
            return null;
        } else {
            productEntityFromDB.setName(productDTORequest.getName());
            productEntityFromDB.setDescription(productDTORequest.getDescription());

            productEntityFromDB.setProductTypeEnum(ProductTypeEnum.USED);
            ProductEntity productEntitySaved = productRepository.save(productEntityFromDB);

            return productEntitySaved.getId() == null ? null : ProductMapper.INSTANCE.entityToDTO(productEntitySaved);
        }
    }

    @Transactional
    public String productDelete(String itemId) {

        ProductEntity productEntityFromDB = getItemEntity(itemId);

        if (productEntityFromDB == null) {
            return null;
        } else {
            productRepository.delete(productEntityFromDB);
            return productEntityFromDB.getId();
        }
    }



}
