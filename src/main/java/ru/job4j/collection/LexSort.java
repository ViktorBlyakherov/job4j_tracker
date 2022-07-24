package ru.job4j.collection;
import java.util.Comparator;

public class LexSort implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        int num1 = Integer.parseInt(left.split("\\.")[0]);
        int num2 = Integer.parseInt(right.split("\\.")[0]);
        return Integer.compare(num1, num2);
    }
}