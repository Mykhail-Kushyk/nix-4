package ua.com.alevel;

import java.util.*;

public class Counter {

    public static TreeMap<Character, Integer> countLetterRepetitions(String arrayLetters) {
        TreeMap<Character, Integer> map = new TreeMap<>(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return o1 - o2;
            }
        });
        for (int i = 0; i < arrayLetters.length(); i++) {
            char symbol = arrayLetters.charAt(i);
            Integer value = map.get(symbol);
            if (value != null) {
                map.put(symbol, value + 1);
            } else {
                map.put(symbol, 1);
            }
        }
        return map;
    }
}