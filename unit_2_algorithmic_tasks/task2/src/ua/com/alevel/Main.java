package ua.com.alevel;

import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        String chars = Isolator.isolateAllBesidesLetters("ns-  _98vn* ыгрыdsj8");
        TreeMap<Character, Integer> treeMap = Counter.countLetterRepetitions(chars);
        for (Character symbol : treeMap.keySet()) {
            System.out.print(symbol + " - " + treeMap.get(symbol));
            System.out.println();
        }
    }
}