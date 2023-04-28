package com.ecom_microservices.order_micorservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "t_order_line_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineItems {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long Id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;

}
