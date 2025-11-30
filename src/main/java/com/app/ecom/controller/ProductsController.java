package com.app.ecom.controller;

import com.app.ecom.dto.ProductsRequest;
import com.app.ecom.dto.ProductsResponse;
import com.app.ecom.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductsController {
    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<List<ProductsResponse>> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductsResponse> updateProduct(@PathVariable long id ,@RequestBody ProductsRequest productsRequest){
        return  productService.updateProduct(id, productsRequest)
                .map(ResponseEntity::ok)
                .orElseGet(
                        ()-> ResponseEntity.notFound().build()
                );
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductsResponse> getProductById(@PathVariable long id){
        return productService.getProductById(id)
                .map(ResponseEntity::ok).orElseGet(
                        ()-> ResponseEntity.notFound().build()
                );
    }

    @PostMapping()
    public ResponseEntity<ProductsResponse> createProduct(@RequestBody ProductsRequest productsRequest){
        return  new ResponseEntity<ProductsResponse>(
                productService.createProduct(productsRequest),
                HttpStatus.CREATED
        );
    }

}
