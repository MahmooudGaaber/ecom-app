package com.app.ecom.service;

import com.app.ecom.dto.CartItemRequest;
import com.app.ecom.entity.CartItem;
import com.app.ecom.entity.Product;
import com.app.ecom.entity.User;
import com.app.ecom.repository.CartItemRepository;
import com.app.ecom.repository.ProductsRepository;
import com.app.ecom.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartItemService {
    private final CartItemRepository cartItemRepository ;
    private final ProductsRepository productsRepository;
    private final UserRepository userRepository;


    public Boolean addToCart(String userId, CartItemRequest request) {
        // ? look for the product if found
        Optional<Product> productOpt = productsRepository.findById(request.getProductId());

        // ! case that product I need to add out of the system
        if (productOpt.isEmpty()){
            return false;
        }

        //?  once I make sure that the product the user try to add is from the list of product in the system
        Product product = productOpt.get();

        //! case that he try to add more than there is in the stock
        if(product.getStockQuantity() < request.getQuantity()){
            return false;
        }

        // ? look for the User if found
        Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));

        // ! case that User Not Valid
        if (userOpt.isEmpty()){
            return false;
        }

        User user = userOpt.get();

        CartItem existingCartItem = cartItemRepository.findByUserAndProduct(userId , product);

        // * case that the item already found in the cart and i will add the number of quantity
        if (existingCartItem != null )
        {
            // ? here all i need to update the quantity
            existingCartItem.setQuantity(existingCartItem.getQuantity() + request.getQuantity());
            existingCartItem.setPrice(product.getPrice().multiply(new BigDecimal(existingCartItem.getQuantity())));
            cartItemRepository.save(existingCartItem);

        } else //* the case that there is no product found in the cart to add as new product in the  cart
        {
            //? here I need to create a new product in the cart
            CartItem cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(request.getQuantity());
            cartItem.setPrice(product.getPrice().multiply(new BigDecimal(request.getQuantity())));
            cartItemRepository.save(cartItem);
        }

        return true;
    }



}
