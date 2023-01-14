package com.kirito.tool.common;

import java.util.HashMap;
import java.util.Map;

public class MyUtil {
    public static final Map<Character, Boolean> wordCells = new HashMap<>(64);

    public static final String myWord = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_-";

    static {
        for (int i = 0; i < myWord.length(); i++) {
            wordCells.put(myWord.charAt(i), true);
        }
    }

    public static boolean isWordCell(char c) {
        return wordCells.containsKey(c);
    }
}
