package com.ecom.productservice.Repository;

import com.ecom.productservice.Model.ProductModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<ProductModel,String> {
}
