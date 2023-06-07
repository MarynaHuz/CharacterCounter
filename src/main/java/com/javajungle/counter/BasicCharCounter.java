package com.javajungle.counter;

import java.util.LinkedHashMap;
import java.util.Map;

public class BasicCharCounter implements CharCounter {
    public Map<Character, Integer> count(String text) {
        Map<Character, Integer> charsCounted = new LinkedHashMap<>();

        for (char character : text.toCharArray()) {
            charsCounted.compute(character, (key, count) -> (count == null) ? 1 : count + 1);
        }

        return charsCounted;
    }
}

