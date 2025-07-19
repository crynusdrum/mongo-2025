package com.mongo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "product-mongo")
@Data
public class ProductEntity {

    @Id
    private String id;
    private String name;
    private String description;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
