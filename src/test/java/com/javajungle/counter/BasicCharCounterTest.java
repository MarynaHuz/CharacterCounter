package com.javajungle.counter;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BasicCharCounterTest {

    @Test
    void count_shouldReturnExpectedCharacterCountMap() {
        BasicCharCounter charCounter = new BasicCharCounter();
        String text = "Hello, world!";
        Map<Character, Integer> expected = Map.of(
                'H', 1,
                'e', 1,
                'l', 3,
                'o', 2,
                ',', 1,
                ' ', 1,
                'w', 1,
                'r', 1,
                'd', 1,
                '!', 1
        );

        Map<Character, Integer> result = charCounter.count(text);

        assertEquals(expected, result);
    }

    @Test
    void count_shouldReturnEmptyMapForEmptyString() {
        BasicCharCounter charCounter = new BasicCharCounter();
        String text = "";
        Map<Character, Integer> expected = Map.of();

        Map<Character, Integer> result = charCounter.count(text);

        assertEquals(expected, result);
    }
}