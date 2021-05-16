package ua.com.alevel.module.second;

import java.util.*;

public class FinderUnique {

    public static String findFirstUnique(List<String> strings) {
        String unique = null;
        for (int i = 0; i < strings.size(); i++) {
            unique = strings.get(i);
            strings.remove(unique);
            if (!strings.contains(unique)) {
                return unique;
            } else {
                strings.add(unique);
                i--;
            }
        }

        return unique;
    }
}