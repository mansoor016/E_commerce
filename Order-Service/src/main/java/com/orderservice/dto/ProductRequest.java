package com.orderservice.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ProductRequest {
    private String id;
    private String name;
    private String brand;
    private double price;
    private int stockQuantiy;
    private boolean available;

    private String categoryID;

    private List<String> imageUrls;
    private List<Atribute> attributes;
}
