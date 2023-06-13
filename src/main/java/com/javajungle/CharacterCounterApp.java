package com.javajungle;

import com.javajungle.cache.Cache;
import com.javajungle.cache.HashMapCache;
import com.javajungle.cache.LRUCache;
import com.javajungle.counter.BasicCharCounter;
import com.javajungle.counter.CachingCharCounterDecorator;
import com.javajungle.counter.CharCounter;
import com.javajungle.formatter.Formatter;

import java.util.*;

public class CharacterCounterApp {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter your text: ");
            String text = scanner.nextLine();

            CharCounter basic = new BasicCharCounter();
            Formatter formatter = new Formatter();
            Map<Character, Integer> result = basic.count(text);
            String formatted = formatter.apply(result);
            System.out.println(formatted);

            Cache<String, Map<Character, Integer>> hashMapCache = new HashMapCache<>();
            CharCounter cachingDecorator = new CachingCharCounterDecorator(basic, hashMapCache);
            Map<Character, Integer> cacheResult = cachingDecorator.count(text);
            String formattedCounter = formatter.apply(cacheResult);
            System.out.println(formattedCounter);

            int capacity = 10; // Set the desired capacity for the LRUCache
            Cache<String, Map<Character, Integer>> lruCache = new LRUCache<>(basic, capacity);
            CharCounter lruCachingDecorator = new CachingCharCounterDecorator(basic, lruCache);
            Map<Character, Integer> lruCachedResult = lruCachingDecorator.count(text);
            String formattedCounterLRU = formatter.apply(lruCachedResult);
            System.out.println(formattedCounterLRU);

            // Reusing the cache
            Map<Character, Integer> resultFromCache = cachingDecorator.count(text);
            String newFormattedCounter = formatter.apply(resultFromCache);
            System.out.println(newFormattedCounter);
        }
    }
}



