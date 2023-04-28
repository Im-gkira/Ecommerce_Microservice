package com.ecommerce_micorservice.inventory_service.Controller;

import com.ecommerce_micorservice.inventory_service.DTO.InventoryResponse;
import com.ecommerce_micorservice.inventory_service.Service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> checkAvailability(@RequestParam List<String> skuCode){
        return inventoryService.checkAvailability(skuCode);
    }

}
