package com.mongo.controller;

import com.mongo.dto.ProductDTO;
import com.mongo.entity.ProductEntity;
import com.mongo.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @PostMapping
    public ResponseEntity<ProductDTO> productCreate(@Valid @RequestBody ProductDTO productDTO){

        ProductDTO productDTOResponse = productService.productCreate(productDTO);

        return productDTOResponse ==null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : ResponseEntity.ok(productDTOResponse);
    }

}
