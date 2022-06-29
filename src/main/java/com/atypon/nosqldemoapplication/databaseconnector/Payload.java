package com.atypon.nosqldemoapplication.databaseconnector;

import lombok.Builder;

import java.util.Collection;
import java.util.Map;

public record Payload(Map<String, Object> criteria, Collection<Map<String, Object>> documents) {

    @Builder
    public Payload {}
}
