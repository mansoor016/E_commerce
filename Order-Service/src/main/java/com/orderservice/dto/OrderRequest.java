package com.orderservice.dto;


import com.orderservice.model.OrderStatus;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderRequest {

//    private String userId;
    private String address;
    private String ph_no;
    private String pincode;
    private OrderStatus orderStatus;
    private List<ItemsRequest> items;



}
