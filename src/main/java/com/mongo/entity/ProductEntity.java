package com.mongo.entity;

import com.mongo.enums.ProductTypeEnum;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document(collection = "product-mongo")
@Data
public class ProductEntity {

    @Id
    private String id;
    private String name;
    private String description;

    @Field("type")
    private ProductTypeEnum productTypeEnum;

    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
