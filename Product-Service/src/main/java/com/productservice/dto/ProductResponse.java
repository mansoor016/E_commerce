package com.productservice.dto;

import com.productservice.model.Atribute;
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
public class ProductResponse {
    private String id;
    private String name;
    private String brand;
    private double price;
    private int stockQuantiy;
    private boolean available;
    private String categoryID;
    private List<String> imageUrls;
    private List<Atribute> attributes;
    private LocalDateTime createdAt;
    private LocalDateTime updated;
}

