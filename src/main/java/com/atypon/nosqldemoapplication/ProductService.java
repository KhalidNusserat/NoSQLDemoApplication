package com.atypon.nosqldemoapplication;

import com.atypon.nosqldemoapplication.databaseconnector.ProductDatabaseCollection;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private final ProductDatabaseCollection productCollection = ProductDatabaseCollection.builder()
            .databaseUrl("http://localhost:8080")
            .database("demo")
            .collection("Product")
            .username("admin")
            .password("admin")
            .build();

    public List<Product> getAll() {
        return productCollection.readItems(Map.of());
    }

    public void save(Product product) {
        productCollection.addItem(product);
    }

    public Product get(String name) {
        return productCollection.readItems(Map.of("name", name)).get(0);
    }

    public void delete(String name) {
        productCollection.removeItems(Map.of("name", name));
    }
}
