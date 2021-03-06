package com.atypon.nosqldemoapplication;

import com.atypon.nosqldemoapplication.databaseconnector.ProductDatabaseCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private String databaseUrl;

    private final ProductDatabaseCollection productCollection = ProductDatabaseCollection.builder()
            .databaseUrl(databaseUrl)
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

    public Product get(String id) {
        return productCollection.readItems(Map.of("_id", id)).get(0);
    }

    public void delete(String id) {
        productCollection.removeItems(Map.of("_id", id));
    }

    @Autowired
    public void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }
}
