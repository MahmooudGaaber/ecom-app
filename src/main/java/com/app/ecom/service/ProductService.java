package com.app.ecom.service;

import com.app.ecom.dto.ProductsRequest;
import com.app.ecom.dto.ProductsResponse;
import com.app.ecom.entity.Product;
import com.app.ecom.repository.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
// ? RequiredArgs Only Deal With Final Variables
@RequiredArgsConstructor
public class ProductService {
    private final ProductsRepository productsRepository;


    public ProductsResponse createProduct (ProductsRequest productsRequest) {
        Product product = new Product();
        ConvertFromProductRequest(product , productsRequest);
        Product savedProduct = productsRepository.save(product);
        return mapToProductResponse(savedProduct);
    }

    public Optional<ProductsResponse> getProductById(Long id){
        return productsRepository.findById(id)
                .map(this::mapToProductResponse);
    }

    public List<ProductsResponse> getAllProducts (){
        return productsRepository.findAll().stream().map(this::mapToProductResponse).collect(Collectors.toList());
    }

    public Optional<ProductsResponse> updateProduct (long id , ProductsRequest productsRequest ) {

        return productsRepository.findById(id)
                .map(existingProduct ->{
                    ConvertFromProductRequest(existingProduct,productsRequest);
                   Product savedProduct = productsRepository.save(existingProduct);
                    return mapToProductResponse(savedProduct);
                });
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
            product.setActive(true);
        }
    }

    public ProductsResponse mapToProductResponse (Product savedProduct){
        ProductsResponse productsResponse = new ProductsResponse();
        productsResponse.setId(savedProduct.getId());
        productsResponse.setCategory(savedProduct.getCategory());
        productsResponse.setDescription(savedProduct.getDescription());
        productsResponse.setImageUrl(savedProduct.getImageUrl());
        productsResponse.setName(savedProduct.getName());
        productsResponse.setPrice(savedProduct.getPrice());
        productsResponse.setStockQuantity(savedProduct.getStockQuantity());
        productsResponse.setActive(savedProduct.getActive());
        return  productsResponse;
    }


}
