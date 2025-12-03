package com.app.ecom.controller;

import com.app.ecom.dto.CartItemRequest;
import com.app.ecom.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cart")
@RequiredArgsConstructor
public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping
    public ResponseEntity<String> addToCart (
            @RequestHeader("X-User-ID") String userId,
            @RequestBody CartItemRequest request
    ){
        if (!cartItemService.addToCart(userId,request)){
            return ResponseEntity.badRequest().body(" Product Out of Stock or User Not Found ");

        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
