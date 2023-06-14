package com.javajungle.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, T> implements Cache<K, T> {

    private final Map<K, T> cacheMap;

    public LRUCache(int capacity) {
        this.cacheMap = new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, T> eldest) {
                return size() > capacity;
            }
        };
    }

    @Override
    public T get(K key) {
        return cacheMap.get(key);
    }

    @Override
    public void put(K key, T value) {
        cacheMap.put(key, value);
    }

    @Override
    public boolean containsKey(K key) {
        return cacheMap.containsKey(key);
    }
}
