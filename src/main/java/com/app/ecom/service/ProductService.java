package com.app.ecom.service;

import com.app.ecom.dto.ProductsRequest;
import com.app.ecom.entity.Product;
import com.app.ecom.entity.User;
import com.app.ecom.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductsRepository productsRepository;


    public void createProduct (ProductsRequest productsRequest) {
        Product product = new Product();
        ConvertFromProductRequest(product , productsRequest);
        productsRepository.save(product);
    }

    public void ConvertFromProductRequest (Product product , ProductsRequest productsRequest){
        product.setCategory(productsRequest.getCategory());
        product.setDescription(productsRequest.getDescription());
        product.setImageUrl(productsRequest.getImageUrl());
        product.setName(productsRequest.getName());
        product.setPrice(productsRequest.getPrice());
        product.setStockQuantity(productsRequest.getStockQuantity());

        if (product.getStockQuantity() <= 0){
            product.setActive(false);
        } else {
            product.setActive(false);
        }
    }


}
