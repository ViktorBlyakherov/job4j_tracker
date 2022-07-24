package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        int minLength = Math.min(left.length(), right.length());
        int rsl = 0;
        for (int i = 0; i < minLength; i++) {
            int char1 = left.charAt(i);
            int char2 = right.charAt(i);
            if (Integer.compare(char1, char2) != 0) {
                rsl = Integer.compare(char1, char2);
                break;
            }
        }
        if (rsl == 0) {
            if (left.length() == right.length()) {
                return rsl;
            } else {
                return left.length() > right.length() ? 1 : -1;
            }
        } else {
            return rsl;
        }
    }
}