package com.atypon.nosqldemoapplication;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize(using = ProductJacksonSerializer.class)
@JsonDeserialize(using = ProductJacksonDeserializer.class)
public final class Product {

    private String id;

    private String name;

    private String countryOfOrigin;

    private double price;
}
