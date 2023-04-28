package com.ecom.productservice.Service;

import com.ecom.productservice.DTO.ProductRequest;
import com.ecom.productservice.DTO.ProductResponse;
import com.ecom.productservice.Model.ProductModel;
import com.ecom.productservice.Repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class ProductService {

    private ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        log.info("request started");
        ProductModel productModel = ProductModel.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(productModel);

        log.info("Product with name:{} and id:{} saved", productModel.getName(), productModel.getId());
    }

    public List<ProductResponse> getAllProduct() {
        return productRepository.findAll().stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(ProductModel productModel){
        return ProductResponse.builder()
                .id(productModel.getId())
                .name(productModel.getName())
                .description(productModel.getDescription())
                .price(productModel.getPrice()).build();
    }
}
