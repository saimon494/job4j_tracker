package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        int rsl = o2.substring(0, 2).compareTo(o1.substring(0, 2));
        if (rsl != 0) {
            return rsl;
        } else {
            return o1.compareTo(o2);
        }
    }
}
