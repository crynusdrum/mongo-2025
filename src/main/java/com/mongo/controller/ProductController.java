package com.mongo.controller;

import com.mongo.dto.ProductDTO;
import com.mongo.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<List<ProductDTO>> retrieveProducts() {
        List<ProductDTO> productDTOListResponse = productService.retrieveProducts();

        return productDTOListResponse ==null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(productDTOListResponse);
    }
    @PostMapping
    public ResponseEntity<ProductDTO> productCreate(@Valid @RequestBody ProductDTO productDTO){

        ProductDTO productDTOResponse = productService.productCreate(productDTO);

        return productDTOResponse ==null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : ResponseEntity.ok(productDTOResponse);
    }

//    @GetMapping()
//    public ResponseEntity<ProductDTO> retrieveProduct(@RequestParam(required = false) String productId){
//
//        ProductDTO productDTOResponse = productService.retrieveProduct(productId);
//
//        return productDTOResponse ==null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(productDTOResponse);
//    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDTO> retrieveProduct(@PathVariable("productId") String productId){

        ProductDTO productDTOResponse = productService.retrieveProduct(productId);

        return productDTOResponse ==null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(productDTOResponse);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductDTO> productUpdate(@PathVariable("productId") String productId, @Valid @RequestBody ProductDTO productDTO){

        ProductDTO productDTOResponse = productService.productUpdate(productId, productDTO);

        return productDTOResponse ==null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(productDTOResponse);
    }


}
