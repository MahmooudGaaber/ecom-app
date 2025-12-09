package com.app.ecom.service;

import com.app.ecom.dto.OrderItemsDTO;
import com.app.ecom.dto.OrderResponse;
import com.app.ecom.entity.Cart;
import com.app.ecom.entity.OrderItems;
import com.app.ecom.entity.Orders;
import com.app.ecom.entity.User;
import com.app.ecom.enums.OrderStatus;
import com.app.ecom.repository.OrderRepository;
import com.app.ecom.repository.UserRepository;
import lombok.Data;
import org.hibernate.query.Order;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        //? we get it by pass one by one by ( Reduce ) and then result saved in totalPrice
        BigDecimal totalPrice = cartItem.stream()
                .map(Cart::getPrice)
                .reduce(BigDecimal.ZERO , BigDecimal::add);

        // finally create order
        Orders orders = new Orders();
        orders.setUser(user);
        orders.setStatus(OrderStatus.CONFIRMED);
        orders.setTotal(totalPrice);
        List<OrderItems> orderItems = cartItem.stream()
                .map(item -> new OrderItems(
                        null,
                        item.getProduct(),
                        item.getQuantity(),
                        item.getPrice(),
                        orders
                )).toList();
        orders.setItems(orderItems);
        Orders savedOrder = orderRepository.save(orders);


        // clear the cart after create order
        cartService.clearCart(userId);
        return Optional.of(mapToOrderResponse(savedOrder));
    }

    private OrderResponse mapToOrderResponse(Orders savedOrder)
    {
        return new OrderResponse(
                savedOrder.getId(),
                savedOrder.getTotal(),
                savedOrder.getStatus(),
                savedOrder.getItems().stream().map(orderItems -> new OrderItemsDTO(
                        orderItems.getId(),
                        orderItems.getProduct().getId(),
                        orderItems.getQuantity(),
                        orderItems.getPrice().multiply(new BigDecimal(orderItems.getQuantity()))
                )).toList(),
                savedOrder.getCreatedAt()
        );
    }


}
