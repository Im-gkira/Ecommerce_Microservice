package com.ecom_microservices.order_micorservice.Controller;

import com.ecom_microservices.order_micorservice.DTO.OrderRequest;
import com.ecom_microservices.order_micorservice.Service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private void createOrder(@RequestBody OrderRequest orderRequest){
        log.info("Placing Order");
        orderService.createOrder(orderRequest);
    }

}
