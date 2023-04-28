package com.ecom_microservices.order_micorservice.Service;

import com.ecom_microservices.order_micorservice.DTO.InventoryResponse;
import com.ecom_microservices.order_micorservice.DTO.OrderLineItemsDto;
import com.ecom_microservices.order_micorservice.DTO.OrderRequest;
import com.ecom_microservices.order_micorservice.Repository.OrderRepository;
import com.ecom_microservices.order_micorservice.model.Order;
import com.ecom_microservices.order_micorservice.model.OrderLineItems;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class OrderService {

    private OrderRepository orderRepository;
    private WebClient.Builder webClient;

    public void createOrder(OrderRequest orderRequest) {

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto).toList();

        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();

        InventoryResponse[] inventoryResponses = webClient.build().get()
                .uri("http://order-service/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class).block();

        assert inventoryResponses != null;
        boolean placeOrder = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::getInStock);

        if (placeOrder) {
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Product not in stock");
        }
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        return OrderLineItems.builder()
                .skuCode(orderLineItemsDto.getSkuCode())
                .price(orderLineItemsDto.getPrice())
                .quantity(orderLineItemsDto.getQuantity())
                .build();
    }
}
