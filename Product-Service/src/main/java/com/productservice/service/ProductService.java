package com.productservice.service;

import com.productservice.Repo.ProductRepository;
import com.productservice.dto.ProductRequest;
import com.productservice.dto.ProductResponse;
import com.productservice.file.ProductFileParser;
import com.productservice.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductFileParser fileParser;

    public void addProduct(ProductRequest productRequest){
        Product product1 = Product.builder()
                .name(productRequest.getName())
                .brand(productRequest.getBrand())
                .price(productRequest.getPrice())
                .stockQuantiy(productRequest.getStockQuantity())
                .available(productRequest.isAvailable())
                .categoryID(productRequest.getCategoryId())
                .imageUrls(productRequest.getImageUrls())
                .attributes(productRequest.getAttributes()).build();
        productRepository.save(product1);

    }

    public List<ProductResponse> getAll(){
        List<Product> product = productRepository.findAll();

        return product.stream().map(this::getAllResponse).toList();
    }

    public ProductResponse getAllResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .brand(product.getBrand())
                .price(product.getPrice())
                .stockQuantiy(product.getStockQuantiy())
                .available(product.isAvailable())
                .categoryID(product.getCategoryID())
                .imageUrls(product.getImageUrls())
                .attributes(product.getAttributes())
                .createdAt(product.getCreatedAt())
                .updated(product.getUpdated())
                .build();
    }

    public String LoadFromJson(){
      try {
          List<ProductRequest> dtos = fileParser.parseJsonFile("/prod.json");
          int saveCount = 0;

              for (ProductRequest dto : dtos) {
                  if (!productRepository.findByName(dto.getName()).isAvailable()) {
                      Product product = new Product();
                      product.setName(dto.getName());
                      product.setBrand(dto.getBrand());
                      product.setPrice(dto.getPrice());
                      product.setStockQuantiy(dto.getStockQuantity());
                      product.setAvailable(dto.isAvailable());
                      product.setCategoryID(dto.getCategoryId());
                      product.setImageUrls(dto.getImageUrls());
                      product.setAttributes(dto.getAttributes());

                      productRepository.save(product);
                      saveCount++;
                  }
              }

          return "Succesfully saved";
      } catch (RuntimeException e) {
          return "Error: " + e.getMessage();
      }
    }

    public ProductResponse getProdByName(String name){
        Product product = productRepository.findByName(name);

        if (product==null){
            throw new RuntimeException("Product not found");
        }
        return getAllResponse(product);
    }

    public void manageStock(String stockId, int quantity){
        Product product = productRepository.findById(stockId).orElseThrow(()->
                new RuntimeException("Product not found.."));

        if (product.getStockQuantiy()<quantity){
            throw new RuntimeException("Insufficient Stock.....");
        }
        int newStock = product.getStockQuantiy()-quantity;
        product.setStockQuantiy(newStock);

        if (newStock==0){
            product.setAvailable(false);
            log.info("Product {} is now out of stock ", product.getName());
        }

        productRepository.save(product);
    }


}
