package com.orderservice.service;

import com.orderservice.dto.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

    @RequiredArgsConstructor
    @Service
    @Slf4j
    public class OrderProducer {
        private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
        private static final String topic = "order-placed";

        public void sendEvent(OrderEvent event){
            log.info("Publishing kafka event: {}", event);
            kafkaTemplate.send(topic, event);
        }
    }
