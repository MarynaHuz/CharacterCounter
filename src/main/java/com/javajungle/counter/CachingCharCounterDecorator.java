package com.javajungle.counter;

import com.javajungle.cache.Cache;

import java.util.Map;

public class CachingCharCounterDecorator implements CharCounter {

    private final CharCounter wrapped;
    private final Cache<String, Map<Character, Integer>> cache;

    public CachingCharCounterDecorator(CharCounter wrapped, Cache<String, Map<Character, Integer>> cache) {
        this.wrapped = wrapped;
        this.cache = cache;
    }

    @Override
    public Map<Character, Integer> count(String text) {
        if (cache.containsKey(text)) {
            return cache.get(text);
        } else {
            Map<Character, Integer> countedChars = wrapped.count(text);
            cache.put(text, countedChars);
            return countedChars;
        }
    }
}
