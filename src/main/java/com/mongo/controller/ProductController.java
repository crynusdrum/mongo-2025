package com.mongo.controller;

import com.mongo.dto.ProductDTO;
import com.mongo.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<Page<ProductDTO>> retrieveProducts(@RequestParam(required = false) String id,
                                                            @RequestParam(required = false) String description,
                                                            Pageable pageable) {
        Page<ProductDTO> productDTOListPageableResponse = productService.retrieveProducts(id, description, pageable);

        return productDTOListPageableResponse ==null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok(productDTOListPageableResponse);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> productCreate(@Valid @RequestBody ProductDTO productDTO){

        ProductDTO productDTOResponse = productService.productCreate(productDTO);

        return productDTOResponse ==null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : ResponseEntity.ok(productDTOResponse);
    }

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

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> productDelete(@PathVariable("productId") String productId){

        String id = productService.productDelete(productId);

        return id ==null ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : ResponseEntity.ok().build();
    }


}
