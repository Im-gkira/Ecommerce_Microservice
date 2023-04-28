package com.ecommerce_micorservice.inventory_service.Service;

import com.ecommerce_micorservice.inventory_service.DTO.InventoryResponse;
import com.ecommerce_micorservice.inventory_service.Model.Inventory;
import com.ecommerce_micorservice.inventory_service.Repository.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Service
public class InventoryService {
    private InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> checkAvailability(List<String> skuData) {
        List<Inventory> inventoryList = inventoryRepository.findBySkuCodeIn(skuData);
        return inventoryList.stream().map(this::mapToInventoryResponse).toList();
    }

    public InventoryResponse mapToInventoryResponse(Inventory inventory) {
        return InventoryResponse.builder().skuCode(inventory.getSkuCode())
                .inStock(inventory.getQuantity() > 0).build();
    }
}
