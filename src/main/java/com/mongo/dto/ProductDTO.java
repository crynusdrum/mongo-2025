package com.mongo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mongo.enums.ProductTypeEnum;
import lombok.Data;

@Data
public class ProductDTO {

    private String id;
    private String name;
    private String description;
    @JsonProperty("type")
    private ProductTypeEnum productTypeEnum;


}
