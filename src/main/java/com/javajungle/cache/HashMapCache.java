package com.javajungle.cache;

import java.util.HashMap;

public class HashMapCache<K, T> implements Cache<K, T> {
    private final HashMap<K, T> cache;

    public HashMapCache() {
        this.cache = new HashMap<>();
    }

    @Override
    public T get(K key) {
        return cache.get(key);
    }

    @Override
    public void put(K key, T value) {
        cache.put(key, value);
    }

    @Override
    public boolean containsKey(K key) {
        return cache.containsKey(key);
    }
}
