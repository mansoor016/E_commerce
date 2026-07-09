package com.orderservice;

import com.orderservice.dto.ProductRequest;
import com.orderservice.service.ProductFiegn;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
@EnableFeignClients  
@EnableJpaAuditing
@SpringBootApplication
public class OrderServiceApplication {

	public static void main(String[] args)	 {
		SpringApplication.run(OrderServiceApplication.class, args);


	}

}







