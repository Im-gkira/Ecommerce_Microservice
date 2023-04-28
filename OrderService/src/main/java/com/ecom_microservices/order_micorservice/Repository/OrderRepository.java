package com.ecom_microservices.order_micorservice.Repository;

import com.ecom_microservices.order_micorservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
