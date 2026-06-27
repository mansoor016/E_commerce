package com.orderservice.dto;

import com.orderservice.model.OrderItems;
import com.orderservice.model.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderResponse {
    private Long id;

    private String userId;
    private String address;
    private String ph_no;
    private String pincode;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private List<ItemsRequest> orderItems;

    private double totalAmount;
    private LocalDateTime createdAt;

    private LocalDateTime updatedAl;
}
