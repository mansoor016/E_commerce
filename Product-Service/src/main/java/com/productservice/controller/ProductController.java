package com.productservice.controller;

import com.productservice.dto.ProductRequest;
import com.productservice.dto.ProductResponse;
import com.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/product")
@RestController
public class ProductController {

    private final ProductService productService;


    @PostMapping
    public void Save(@RequestBody ProductRequest productRequest){
        productService.addProduct(productRequest);
    }

    @GetMapping
    public List<ProductResponse> productList(){
        return productService.getAll();
    }
    @PostMapping("/json")
    public String saveFromJson(){
        return productService.LoadFromJson();
    }

    @GetMapping("/name/{name}")
    public ProductResponse getByName(@PathVariable String name){
        return productService.getProdByName(name);
    }

}
