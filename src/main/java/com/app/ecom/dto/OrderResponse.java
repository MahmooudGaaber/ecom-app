package com.app.ecom.dto;

import com.app.ecom.enums.OrderStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {
    private Long id;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private List<OrderItemsDTO> items;
    private LocalDateTime createdAt;
}
