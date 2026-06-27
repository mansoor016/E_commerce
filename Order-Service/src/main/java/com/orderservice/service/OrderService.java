package com.orderservice.service;

import com.orderservice.dto.ItemsRequest;
import com.orderservice.dto.OrderRequest;
import com.orderservice.dto.OrderResponse;
import com.orderservice.dto.ProductRequest;
import com.orderservice.model.Order;
import com.orderservice.model.OrderItems;
import com.orderservice.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductFiegn productFiegn;

    public void MakeOreder(OrderRequest orderRequest){

        Order order = Order.builder()
                .userId(orderRequest.getUserId())
                .address(orderRequest.getAddress())
                .ph_no(orderRequest.getPh_no())
                .pincode(orderRequest.getPincode())
                .orderStatus(orderRequest.getOrderStatus())
                .build();

        List<OrderItems> itemsRequests = orderRequest.getItems().stream().map(
                items->{
                    ProductRequest product = productFiegn.getProductByName(items.getProductName());

                    if (!product.isAvailable()){
                        throw new RuntimeException(items.getProductName()+" is not available");
                    }
                    if (items.getQuantity()>product.getStockQuantiy()){
                        throw new RuntimeException("Sorry! stocks are not available");
                    }

                    return OrderItems.builder()
                            .productId(product.getId())
                            .productName(items.getProductName())
                            .price(product.getPrice())
                            .quantity(items.getQuantity())
                            .order(order)
                            .build();

                }
        ).toList();

        order.setOrderItems(itemsRequests);
        double total = itemsRequests.stream().mapToDouble(i->i.getPrice()*i.getQuantity()).sum();
        order.setTotalAmount(total);
        orderRepository.save(order);
    }

    public List<OrderResponse> showAll(){
        List<Order> order = orderRepository.findAll();
        return order.stream().map(this::toOrderResponse).toList();
    }

    public OrderResponse toOrderResponse(Order order){
        OrderResponse orderResponse= OrderResponse.builder()
                .userId(order.getUserId())
                .address(order.getAddress())
                .ph_no(order.getAddress())
                .pincode(order.getPincode())
                .orderStatus(order.getOrderStatus())
                .createdAt(order.getCreatedAt())
                .updatedAl(order.getUpdatedAl())
                .build();
        List<ItemsRequest> itemsRequests = order.getOrderItems().stream().map(
                orderItems -> {
                   return ItemsRequest.builder()
                            .productId(orderItems.getProductId())
                            .productName(orderItems.getProductName())
                            .price(orderItems.getPrice())
                            .quantity(orderItems.getQuantity())
                            .build();
                }
        ).toList();

        orderResponse.setOrderItems(itemsRequests);
        double total = itemsRequests.stream().mapToDouble(i->i.getPrice()*i.getQuantity()).sum();
        orderResponse.setTotalAmount(total);

        return orderResponse;
    }
}
