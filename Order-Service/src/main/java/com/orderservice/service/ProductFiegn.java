package com.orderservice.service;

import com.orderservice.dto.ProductRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
//, url = "http://localhost:8181"
@FeignClient(name = "Product-Service")
public interface ProductFiegn {

    @GetMapping("/product/name/{name}")
     ProductRequest getProductByName(@PathVariable("name") String name);
}
