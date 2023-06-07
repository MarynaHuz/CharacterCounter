package com.javajungle.formatter;

import java.util.Map;

public class Formatter {

    public String apply(Map<Character, Integer> result) {

        StringBuilder formattedCharCounter = new StringBuilder();

        for (Map.Entry<Character, Integer> entry : result.entrySet()) {
            Character key = entry.getKey();
            Integer value = entry.getValue();
            formattedCharCounter.append(String.format("\"%s\" - %s%s", key, value, System.lineSeparator()));
        }
        return formattedCharCounter.toString();
    }
}
