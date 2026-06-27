    package com.productservice.file;

    import com.fasterxml.jackson.databind.ObjectMapper;
    import com.productservice.dto.ProductRequest;
    import com.fasterxml.jackson.core.type.TypeReference;

    import com.productservice.dto.ProductResponse;
    import lombok.RequiredArgsConstructor;

    import org.springframework.stereotype.Component;

    import java.io.InputStream;
    import java.util.List;

    @RequiredArgsConstructor
    @Component
    public class ProductFileParser {

        private final ObjectMapper objectMapper;

        public List<ProductRequest> parseJsonFile(String filePath){
            try(InputStream inputStream = getClass().getResourceAsStream(filePath)){
                if (inputStream == null){
                    throw new RuntimeException("File not found: " + filePath);
                }
                return objectMapper.readValue(inputStream, new TypeReference<List<ProductRequest>>() {
                });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
