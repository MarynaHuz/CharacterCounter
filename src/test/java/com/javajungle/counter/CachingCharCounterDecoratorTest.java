package com.javajungle.counter;

import com.javajungle.cache.Cache;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CachingCharCounterDecoratorTest {

    @Mock
    CharCounter counter;

    @Mock
    Cache<String, Map<Character, Integer>> cache;

    @InjectMocks
    CachingCharCounterDecorator decorator;

    @Test
    void count_shouldCountCharactersForFirstTime() {
        String text = "Hello, world!";
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('H', 1);
        expected.put('e', 1);
        expected.put('l', 3);
        expected.put('o', 2);
        expected.put(',', 1);
        expected.put(' ', 1);
        expected.put('w', 1);
        expected.put('r', 1);
        expected.put('d', 1);
        expected.put('!', 1);

        when(cache.containsKey(text)).thenReturn(false);
        when(counter.count(text)).thenReturn(expected);

        Map<Character, Integer> result = decorator.count(text);

        assertEquals(expected, result);
        verify(cache).put(text, expected);
    }


    @Test
    void count_shouldRetrieveCountFromCache() {
        String text = "Hello, world!";
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('H', 1);
        expected.put('e', 1);
        expected.put('l', 3);
        expected.put('o', 2);
        expected.put(',', 1);
        expected.put(' ', 1);
        expected.put('w', 1);
        expected.put('r', 1);
        expected.put('d', 1);
        expected.put('!', 1);

        when(cache.containsKey(text)).thenReturn(true);
        when(cache.get(text)).thenReturn(expected);

        Map<Character, Integer> result = decorator.count(text);

        assertEquals(expected, result);
        verify(cache, never()).put(anyString(), anyMap());
        verify(counter, never()).count(anyString());
    }
}
