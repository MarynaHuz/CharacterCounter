package com.javajungle.cache;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class LRUCacheTest {

    private final Cache<Integer, Integer> cache = new LRUCache<>(3);

    @Test
    void should_AddValues() {
        cache.put(1, 11);
        cache.put(2, 22);
        cache.put(3, 33);

        assertEquals(11, cache.get(1));
        assertEquals(22, cache.get(2));
        assertEquals(33, cache.get(3));
    }

    @Test
    void should_EvictLeastAdded() {
        cache.put(1, 11);
        cache.put(2, 22);
        cache.put(3, 33);
        cache.put(4, 44);

        assertFalse(cache.containsKey(1));
        assertEquals(22, cache.get(2));
        assertEquals(33, cache.get(3));
        assertEquals(44, cache.get(4));
    }

    @Test
    void should_EvictLeastUsed() {
        cache.put(1, 11);
        cache.put(2, 22);
        cache.put(3, 33);

        cache.get(1);
        cache.get(2);

        cache.put(4, 44);

        assertFalse(cache.containsKey(3));
        assertEquals(11, cache.get(1));
        assertEquals(22, cache.get(2));
        assertEquals(44, cache.get(4));
    }
}
