package com.notificationservice.service;

import com.notificationservice.dto.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {
    public void sendNotification(OrderEvent event){
        log.info("----------------------------");
        log.info("Notification Received..");
        log.info("Order ID: {} ", event.getOrderId());
        log.info("User ID    : {}", event.getUserId());
        log.info("Amount     : {}", event.getTotalAmount());
        log.info("Status     : {}", event.getStatus());
        log.info("Message    : Dear Customer, your Order #{} has been {}. Total: Rs.{}",
                event.getOrderId(),
                event.getStatus(),
                event.getTotalAmount());
        log.info("====================================");
    }
}
