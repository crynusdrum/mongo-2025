package com.mongo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product-mongo")
@Data
public class ProductEntity {

    @Id
    private String id;
    private String name;
    private String description;

//    public Product(String id, String name, String description) {
//        this.id = id;
//        this.name = name;
//        this.description = description;
//    }
//
//    public Product(String description, String name) {
//        this.description = description;
//        this.name = name;
//    }
//
//    public Product() {
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getId() {
//        return id;
//    }


}
