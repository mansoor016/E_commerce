package com.orderservice.dto;

import com.orderservice.model.OrderStatus;
import lombok.Data;

import java.util.List;

@Data
public class TestRequest {

    private String userId;
    private String address;
    private String ph_no;
    private String pincode;
    private OrderStatus orderStatus;
    private List<ItemsRequest> items;
    private double totalAmount;
}
