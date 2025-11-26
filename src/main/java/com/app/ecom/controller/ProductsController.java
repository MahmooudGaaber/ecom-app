package com.app.ecom.controller;

import com.app.ecom.dto.ProductsRequest;
import com.app.ecom.dto.ProductsResponse;
import com.app.ecom.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(name = "/api/products")
public class ProductsController {
    private final ProductsRepository productsRepository;

    // Creating And Updating Products

    @GetMapping("products")
    public ResponseEntity<ProductsResponse> getAllProducts(){
        return null;
    }

    @PutMapping("product/{id}")
    public Boolean updateProduct(){
        return null ;
    }

    @GetMapping("products/{id}")
    public ResponseEntity<ProductsResponse> getProductById(){
        return null;
    }

    @PostMapping("Product")
    public ResponseEntity<ProductsResponse> createProduct(@RequestBody ProductsRequest productsRequest){
        return null;
    }

}
