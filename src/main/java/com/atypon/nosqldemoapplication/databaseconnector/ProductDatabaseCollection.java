package com.atypon.nosqldemoapplication.databaseconnector;

import com.atypon.nosqldemoapplication.Product;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.squareup.okhttp.Credentials;
import lombok.Builder;

import java.io.UncheckedIOException;
import java.util.List;
import java.util.Map;

public class ProductDatabaseCollection implements DatabaseCollection<Product> {

    private final String database;

    private final String collection;

    private final ObjectMapper mapper;

    private final String databaseUrl;

    private final DatabaseRestClient restClient;

    private final TypeReference<Map<String, Object>> mapType = new TypeReference<>() {
    };

    private final TypeReference<List<Product>> listType = new TypeReference<>() {
    };

    @Builder
    public ProductDatabaseCollection(
            String databaseUrl,
            String database,
            String collection,
            String username,
            String password) {
        this.databaseUrl = databaseUrl;
        this.database = database;
        this.collection = collection;
        String credentials = Credentials.basic(username, password);
        restClient = new DatabaseRestClient(credentials);
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public void addItem(Product product) {
        String postRequestUrl = String.format(
                "%s/databases/%s/collections/%s/documents",
                databaseUrl,
                database,
                collection
        );
        Payload payload = Payload.builder().documents(List.of(objectToMap(product))).build();
        restClient.postRequest(postRequestUrl, objectToJson(payload));
    }

    private Map<String, Object> objectToMap(Object object) {
        try {
            String json = mapper.writeValueAsString(object);
            return mapper.readValue(json, mapType);
        } catch (JsonProcessingException e) {
            throw new UncheckedIOException(e);
        }
    }

    private String objectToJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public List<Product> readItems(Map<String, Object> criteria) {
        String searchUrl = String.format(
                "%s/databases/%s/collections/%s/documents/searches",
                databaseUrl,
                database,
                collection
        );
        Payload payload = Payload.builder().criteria(criteria).build();
        String token = restClient.postRequest(searchUrl, objectToJson(payload));
        String resultJson = restClient.getRequest(searchUrl + "/" + token);
        try {
            Map<String, Object> result = mapper.readValue(resultJson, mapType);
            String documentsJson = mapper.writeValueAsString(result.get("result"));
            return mapper.readValue(documentsJson, listType);
        } catch (JsonProcessingException e) {
            throw new UncheckedIOException(e);
        }
    }

    @Override
    public void removeItems(Map<String, Object> criteria) {
        String requestUrl = String.format(
                "%s/databases/%s/collections/%s/documents/deleted-documents",
                databaseUrl,
                database,
                collection
        );
        Payload payload = Payload.builder().criteria(criteria).build();
        restClient.postRequest(requestUrl, objectToJson(payload));
    }
}
