package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String[] array1 = o1.split("/");
        String[] array2 = o2.split("/");
        int rsl;
        if (array1[0].equals(array2[0])) {
            rsl = o1.substring(array1[0].length()).compareTo(o2.substring(array2[0].length()));
        } else {
            rsl = array2[0].compareTo(array1[0]);
        }
        return rsl;
    }
}