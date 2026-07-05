package com.orderservice.exception;

import javax.naming.InsufficientResourcesException;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message){
        super(message);
    }
}
