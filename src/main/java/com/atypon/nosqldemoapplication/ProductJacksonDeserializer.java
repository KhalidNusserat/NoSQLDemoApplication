package com.atypon.nosqldemoapplication;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Map;

public class ProductJacksonDeserializer extends JsonDeserializer<Product> {

    private static final TypeReference<Map<String, Object>> mapType = new TypeReference<>() {
    };

    @Override
    public Product deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        Map<String, Object> map = p.readValueAs(mapType);
        return Product.builder()
                .id((String) map.get("_id"))
                .name((String) map.get("name"))
                .countryOfOrigin((String) map.get("countryOfOrigin"))
                .price((Double) map.get("price"))
                .build();
    }
}
