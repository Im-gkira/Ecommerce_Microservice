package com.ecom.productservice.Controller;

import com.ecom.productservice.DTO.ProductRequest;
import com.ecom.productservice.DTO.ProductResponse;
import com.ecom.productservice.Service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<ProductResponse> getAllProduct(){
        return productService.getAllProduct();
    }
}
