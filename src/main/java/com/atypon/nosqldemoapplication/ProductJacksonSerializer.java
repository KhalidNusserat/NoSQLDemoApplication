package com.atypon.nosqldemoapplication;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Map;

public class ProductJacksonSerializer extends JsonSerializer<Product> {
    @Override
    public void serialize(Product value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        Map<String, Object> productMap = Map.of(
                "name", value.getName(),
                "countryOfOrigin", value.getCountryOfOrigin(),
                "price", value.getPrice()
        );
        gen.writeObject(productMap);
    }
}
