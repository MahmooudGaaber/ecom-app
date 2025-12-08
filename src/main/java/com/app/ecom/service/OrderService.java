package com.app.ecom.service;

import com.app.ecom.dto.OrderResponse;
import com.app.ecom.entity.Cart;
import com.app.ecom.entity.User;
import com.app.ecom.repository.OrderRepository;
import com.app.ecom.repository.UserRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final UserRepository userRepository;


    public Optional<OrderResponse> createOrder(String userId)
    {
        // validate for cart items
        //? by see if the item is in the cart
        List<Cart> cartItem = cartService.getAllItemsOnCart(userId);
        if (cartItem.isEmpty()){
            return Optional.empty();
        }

        // validate for user
        //? by see if user is valid in system
        Optional<User> userOptional = userRepository.findById(Long.valueOf(userId));
        if (userOptional.isEmpty()){
            return Optional.empty();
        }
        User user = userOptional.get();

        // calculate total price

        // finally create order


        // clear the cart after create order

        return null;
    }


}
