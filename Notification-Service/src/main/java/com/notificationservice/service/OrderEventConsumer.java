package com.notificationservice.service;


import com.notificationservice.dto.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class OrderEventConsumer {

    private final NotificationService notificationService;

    @KafkaListener(
            topics = "order-placed",
            groupId = "notification-group"
    )
    public void consumeOrderEvent(OrderEvent event){
      log.info("Kafka event received for OrderId {} ", event.getOrderId());
      notificationService.sendNotification(event);
    }
}
