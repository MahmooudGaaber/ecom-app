package com.app.ecom.service;

import com.app.ecom.dto.CartItemRequest;
import com.app.ecom.entity.Cart;
import com.app.ecom.entity.Product;
import com.app.ecom.entity.User;
import com.app.ecom.repository.CartRepository;
import com.app.ecom.repository.ProductsRepository;
import com.app.ecom.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
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

        Cart existingCart = cartRepository.findByUserAndProduct(user , product);

        // * case that the item already found in the cart and i will add the number of quantity
        if (existingCart != null )
        {
            // ? here all i need to update the quantity
            existingCart.setQuantity(existingCart.getQuantity() + request.getQuantity());
            existingCart.setPrice(product.getPrice().multiply(new BigDecimal(existingCart.getQuantity())));
            cartRepository.save(existingCart);

        } else //* the case that there is no product found in the cart to add as new product in the  cart
        {
            //? here I need to create a new product in the cart
            Cart cart = new Cart();
            cart.setUser(user);
            cart.setProduct(product);
            cart.setQuantity(request.getQuantity());
            cart.setPrice(product.getPrice().multiply(new BigDecimal(request.getQuantity())));
            cartRepository.save(cart);
        }

        return true;
    }




    public Boolean removeFromCart(
            String userId,
            Long productId
    ){

        // ? look for the product if found
        Optional<Product> productOpt = productsRepository.findById(productId);
        Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));

       if(
               productOpt.isPresent() &&
               userOpt.isPresent()
       ){
           cartRepository.deleteByUserAndProduct(userOpt.get(),productOpt.get());
           return true;
       }
        return false;
    }

    public List<Cart> getAllItemsOnCart(
            String userId
    ){
        Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));

        if(userOpt.isPresent()){
            return cartRepository.findAll();
        }

        return null;
    }




}
