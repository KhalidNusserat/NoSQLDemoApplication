package com.atypon.nosqldemoapplication;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public final class Product {

    private String name;

    private String countryOfOrigin;

    private double price;
}
