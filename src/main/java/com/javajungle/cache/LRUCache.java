package com.javajungle.cache;

import com.javajungle.counter.CharCounter;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, T> implements Cache<K, T> {

    private final int capacity;
    private final Map<K, T> cacheMap;
    private final CharCounter charCounter;

    public LRUCache(CharCounter charCounter, int capacity) {
        this.charCounter = charCounter;
        this.capacity = capacity;
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
