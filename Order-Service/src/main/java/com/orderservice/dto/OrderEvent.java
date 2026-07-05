package com.orderservice.dto;

import com.orderservice.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderEvent {
    private Long orderId;
    private String userId;
    private double totalAmount;
    private OrderStatus status;

}
