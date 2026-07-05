package com.orderservice.exception;

import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = ErrorResponse.builder()
                .status(404)
                .error("Not Found")
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }


    // Insufficient stock — 400
    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientStock(
            InsufficientStockException ex) {

        ErrorResponse error = ErrorResponse.builder()
                .status(400)
                .error("Bad Request")
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // Feign error — 503
//    @ExceptionHandler(feign.FeignException.class)
//    public ResponseEntity<ErrorResponse> handleFeignException(
//            feign.FeignException ex) {
//
//        ErrorResponse error = ErrorResponse.builder()
//                .status(503)
//                .error("Service Unavailable")
//                .message("Dependent service is not available!")
//                .timestamp(LocalDateTime.now())
//                .build();
//
//        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        // Feign error hai check karo
        if (ex.getCause() instanceof feign.FeignException feignEx) {
            ErrorResponse error = ErrorResponse.builder()
                    .status(503)
                    .error("Service Unavailable")
                    .message("Product Service is not available! Try again later.")
                    .timestamp(LocalDateTime.now())
                    .build();
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
        }

        // Baaki sab errors
        ErrorResponse error = ErrorResponse.builder()
                .status(500)
                .error("Internal Server Error")
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
