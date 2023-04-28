package com.ecommerce_micorservice.inventory_service.Repository;

import com.ecommerce_micorservice.inventory_service.Model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {

    List<Inventory> findBySkuCodeIn(List<String> orderData);
}
