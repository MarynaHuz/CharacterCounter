package com.javajungle.cache;

import com.javajungle.counter.CharCounter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class LRUCacheTest {

    @Mock
    CharCounter charCounter;

    private LRUCache<String, Map<Character, Integer>> cache;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cache = new LRUCache<>(charCounter, 3);
    }

    @Test
    void get_shouldReturnStoredValue() {
        String key = "Key";
        Map<Character, Integer> value = Map.of('A', 1, 'B', 2, 'C', 3);
        cache.put(key, value);

        Map<Character, Integer> expectedResult = value;

        Map<Character, Integer> result = cache.get(key);

        assertEquals(expectedResult, result);
        verify(charCounter, never()).count(anyString());
    }


    @Test
    void get_shouldReturnNullIfKeyNotPresent() {
        String key = "Key";

        Map<Character, Integer> result = cache.get(key);

        assertNull(result);
        verify(charCounter, never()).count(anyString());
    }

    @Test
    void put_shouldStoreValueInCache() {
        String key = "Key";
        Map<Character, Integer> value = Map.of('A', 1, 'B', 2, 'C', 3);

        cache.put(key, value);

        assertTrue(cache.containsKey(key));
        verify(charCounter, never()).count(anyString());
    }

    @Test
    void put_shouldReplaceValueIfKeyAlreadyExists() {
        String key = "Key";
        Map<Character, Integer> firstValue = Map.of('A', 1, 'B', 2, 'C', 3);
        Map<Character, Integer> secondValue = Map.of('X', 10, 'Y', 20, 'Z', 30);

        cache.put(key, firstValue);
        cache.put(key, secondValue);

        assertTrue(cache.containsKey(key));
        assertEquals(secondValue, cache.get(key));
        verify(charCounter, never()).count(anyString());
    }

    @Test
    void containsKey_shouldReturnTrueForExistingKey() {
        String key = "Key";
        Map<Character, Integer> value = Map.of('A', 1, 'B', 2, 'C', 3);

        cache.put(key, value);

        assertTrue(cache.containsKey(key));
        verify(charCounter, never()).count(anyString());
    }

    @Test
    void containsKey_shouldReturnFalseForNonexistentKey() {
        String key = "Key";
        String nonexistentKey = "NonexistentKey";
        Map<Character, Integer> value = Map.of('A', 1, 'B', 2, 'C', 3);

        cache.put(key, value);

        assertFalse(cache.containsKey(nonexistentKey));
        verify(charCounter, never()).count(anyString());
    }
}
