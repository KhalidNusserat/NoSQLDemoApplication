package com.atypon.nosqldemoapplication.databaseconnector;

import java.util.Collection;
import java.util.Map;

public interface DatabaseCollection<T> {

    void addItem(T item);

    Collection<T> readItems(Map<String, Object> criteria);

    void removeItems(Map<String, Object> criteria);
}
