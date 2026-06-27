package com.orderservice.controller;

import com.orderservice.dto.OrderRequest;
import com.orderservice.dto.OrderResponse;
import com.orderservice.dto.ProductRequest;
import com.orderservice.dto.TestRequest;
import com.orderservice.model.Order;
import com.orderservice.service.OrderService;
import com.orderservice.service.ProductFiegn;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

//    private final ProductFiegn productFiegn;

//    @PostMapping("/manual")
//    public void save(@RequestBody OrderRequest order){
//        orderService.makeOrders(order);
//    }


    @PostMapping
    public void makeOrder(@RequestBody OrderRequest orderRequest){
        orderService.MakeOreder(orderRequest);
    }

    @GetMapping
    public List<OrderResponse> showAll(){
        return orderService.showAll();
    }

    @PostMapping("/test")
    public ResponseEntity<String> testSave(@RequestBody OrderRequest body){
        System.out.println("Body comes "+ body);
        return ResponseEntity.status(HttpStatus.CREATED).body("Order placed!");
    }

//    @GetMapping("/item/{name}")
//    public ProductRequest getItem(@PathVariable String name){
//        return productFiegn.getProductByName(name);
//    }



}
