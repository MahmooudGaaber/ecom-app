package com.app.ecom.service;

import com.app.ecom.dto.ProductsRequest;
import com.app.ecom.dto.ProductsResponse;
import com.app.ecom.entity.Product;
import com.app.ecom.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductsRepository productsRepository;


    public void createProduct (ProductsRequest productsRequest) {
        Product product = new Product();
        productsRepository.save(product);
    }
}
